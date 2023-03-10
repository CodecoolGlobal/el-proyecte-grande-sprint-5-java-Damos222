import '../css/RegistrationForm.css';
import React, { useState } from 'react'

const RegistrationForm = (props) => {
    const INITIAL_DATA = {
        email: "",
        password: "",
        firstName: "",
        lastName: ""
    }
    const [formData, setFormData] = useState(INITIAL_DATA)
    const [confirmPassword, setConfirmPassword] = useState("")

    function updateData(newInput) {
        setFormData({
            ...formData,
            ...newInput
        })
    }

    function handleConfirmPasswordChange(e) {
        setConfirmPassword(e.target.value)
    }

    async function onSubmit(e) {
        e.preventDefault()
        if (formData.password !== confirmPassword) {
            alert("Passwords do not match")
            return
        }
        const emailTaken = await isEmailTaken()
        if (emailTaken === true) {
            alert("Account with this email already exists")
        } else {
            registerUser()
        }
    }

    async function isEmailTaken() {
        return fetch(`http://localhost:8080/auth/emailTaken/${formData.email}`, {
            method: "GET",
            mode: 'cors'
        })
            .then(res => res.json())
            .then(json => json.emailTaken)
            .catch(error => console.log(error))
    }

    async function registerUser() {
        try {
            let res = await fetch("http://localhost:8080/auth/register", {
                method: "POST",
                body: JSON.stringify(formData),
                headers: {
                    "Content-Type": "application/json"
                },
            })
            if (res.ok) {
                const token = await res.text()
                console.log(token)
                localStorage.setItem('token', token)
                alert("Account created successfully")
                props.setShowRegistrationModal(false)
            }
        } catch (error) {
            console.log(error)

        }
    }


    return (
        <div className='accountDataForm'>
            <form onSubmit={onSubmit}>
                <label>first name:</label>
                <input type='text' id='firstName' name='firstName'
                    onChange={(e) => updateData({ firstName: e.target.value })} required></input>
                <label>last name:</label>
                <input type='text' id='lastName' name='lastName'
                    onChange={(e) => updateData({ lastName: e.target.value })} required></input>
                <label>email:</label>
                <input type="email" id='email' name='email'
                    onChange={(e) => updateData({ email: e.target.value })} />
                <label>password (must contain 8 characters, a capital letter, an uppercase letter, a digit):</label>
                <input type="password" name="password" id="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                    title="Must contain 8 characters, a capital letter, an uppercase letter, a digit"
                    onChange={(e) => updateData({ password: e.target.value })} />
                <label>repeat password:</label>
                <input type="password" name="password2" id="password2" onChange={handleConfirmPasswordChange} />
                <button className="submitButton" type="submit" required>Register</button>
            </form>
        </div>
    )
}

export default RegistrationForm