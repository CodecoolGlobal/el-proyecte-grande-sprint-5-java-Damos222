import '../css/Header.css';
import logo from "../assets/logo.png";
import { useState } from "react";
import Modal from "./Modal";
import LoginForm from "./LoginForm";
import RegistrationForm from "./RegistrationForm";

const Header = () => {
    const [showLoginModal, setShowLoginModal] = useState(false)
    const [showRegistrationModal, setShowRegistrationModal] = useState(false)

    return (
        <nav className="header">
            <div>
                <img src={logo} alt="logo" width="350" />
            </div>
            <div className="links">
                <button className="log-in-button" onClick={() => setShowLoginModal(true)}>Login</button>
                <button className="sign-up-button" onClick={() => setShowRegistrationModal(true)}>Sign Up</button>
            </div>
            <Modal open={showLoginModal} onClose={() => setShowLoginModal(false)}>
                <LoginForm />
            </Modal>
            <Modal open={showRegistrationModal} onClose={() => setShowRegistrationModal(false)}>
                <RegistrationForm />
            </Modal>
        </nav >);
}

export default Header;