import '../css/LoginForm.css';
import React from 'react'

const LoginForm = () => {
    return (
        <div className='accountDataForm'>
            <form>
                <label>
                    email:
                    <input type="email" id='email' name='email' />
                </label>
                <br />
                <label>
                    password:
                    <input type="password" name="password" id="password" />
                </label>
                <br />
                <button className="submitButton" type="submit">Login</button>
            </form>
        </div>
    )
}

export default LoginForm

