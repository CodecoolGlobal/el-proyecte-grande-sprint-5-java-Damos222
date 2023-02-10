import '../css/LoginForm.css';
import React from 'react'
import { useState } from 'react';

const LoginForm = (props) => {
    const [data, setData] = useState({ email: "", password: "" })

    async function login(e) {
        e.preventDefault()
        console.log(data)
        const response = await fetch('http://localhost:8080/auth/authenticate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(data),
            mode: 'cors'
        })
        if (response.ok) {
            const token = await response.text()
            console.log(token)
            localStorage.setItem('token', token)
            props.setLoggedIn(true)
            props.setShowLoginModal(false)
            console.log(props.loggedIn)
        } else {
            alert("Wrong username or password")
        }
    }

    function handleChange(newValue) {
        setData({ ...data, ...newValue })
    }

    return (
        <div className='accountDataForm'>
            <form onSubmit={login}>
                <label>email:</label>
                    <input type="email" id='email' name='email' onChange={(e) => handleChange({email: e.target.value})} />
                <label>password:</label>
                    <input type="password" name="password" id="password" onChange={(e) => handleChange({password: e.target.value})} />
                <button className="submitButton" type="submit">Login</button>
            </form>
        </div>
    )
}

export default LoginForm

