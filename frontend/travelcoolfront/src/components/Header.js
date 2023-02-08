import '../css/Header.css';
import logo from "../assets/logo.png";
import {useState} from "react";
import Modal from "./Modal";
import LoginForm from "./LoginForm";
import RegistrationForm from "./RegistrationForm";
import {Link} from "react-router-dom";

const Header = () => {
    const [showLoginModal, setShowLoginModal] = useState(false)
    const [showRegistrationModal, setShowRegistrationModal] = useState(false)

    return (
        <nav className="header">
            <div>
                <Link to="/">
                    <img src={logo} alt="logo" width="350"/>
                </Link>
            </div>
            <div className="links">
                <button className="log-in-button" onClick={() => setShowLoginModal(true)}>Login</button>
                <button className="sign-up-button" onClick={() => setShowRegistrationModal(true)}>Sign Up</button>
                <a href={"http://localhost:3000/addAccommodation/"}>
                    <button className="see-details">Add accommodation</button>
                </a>
            </div>
            <Modal open={showLoginModal} onClose={() => setShowLoginModal(false)}>
                <LoginForm setLoggedIn={props.setLoggedIn}/>
            </Modal>
            <Modal open={showRegistrationModal} onClose={() => setShowRegistrationModal(false)}>
                <RegistrationForm/>
            </Modal>
        </nav>);
}

export default Header;