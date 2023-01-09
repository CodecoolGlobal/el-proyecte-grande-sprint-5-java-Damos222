import logo from "../assets/logo.png";
import { useState } from "react";
import Modal from "./Modal";

const NavBar = () => {
    const [showLoginModal, setShowLoginModal] = useState(false)
    const [showRegistrationModal, setShowRegistrationModal] = useState(false)

    return (
        <nav className="navbar">
            <div>
                <img src={logo} alt="logo" width="350" />
            </div>
            <div className="links">
                < button className="log-in-button" onClick={() => setShowLoginModal(true)}>Login</button>
                <button className="sign-up-button" onClick={() => setShowRegistrationModal(true)}>Registration</button>
            </div>
            <Modal open={showLoginModal} onClose={() => setShowLoginModal(false)}>
                <div className='accountDataForm'>
                    <form>
                        <label>
                            email:
                            <input type="email" id='email' name='email'/>
                        </label>
                        <br/>
                        <label>
                            password:
                            <input type="password" name="password" id="password" />
                        </label>
                        <br/>
                        <button className="submitButton" type='submit'>Login</button>
                    </form>
                </div>
            </Modal>
            <Modal open={showRegistrationModal} onClose={() => setShowRegistrationModal(false)}>
                <div className='accountDataForm'>
                    <form>
                        <label>
                            email:
                            <input type="email" id='email' name='email' />
                        </label>
                        <br/>
                        <label>
                            password:
                            <br/>
                            <input type="password" name="password" id="password" />
                        </label>
                        <br/>
                        <label>
                            repeat password:
                            <br/>
                            <input type="password" name="password2" id="password2" />
                        </label>
                        <br/>
                        <button className="submitButton" type='submit'>Login</button>
                    </form>
                </div>
            </Modal>
        </nav >);
}

export default NavBar;