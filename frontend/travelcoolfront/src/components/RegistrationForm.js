import '../css/RegistrationForm.css';
import React from 'react'

const RegistrationForm = () => {
    return (
        <div className='accountDataForm'>
            <form>
                <label>
                    email:
                    <input type="email" id='email' name='email' />
                </label>
                <br />
                <label>
                    password (must contain 8 characters, a capital letter, an uppercase letter, a digit):
                    <input type="password" name="password" id="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                        title="Must contain 8 characters, a capital letter, an uppercase letter, a digit" />
                </label>
                <br />
                <label>
                    repeat password:
                    <input type="password" name="password2" id="password2" />
                </label>
                <br />
                <button className="submitButton" type="submit">Register</button>
            </form>
        </div>
    )
}

export default RegistrationForm