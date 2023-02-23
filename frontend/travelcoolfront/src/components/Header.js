import '../css/Header.css';
import logo from "../assets/logo.png";
import { Link } from "react-router-dom";
import AccountMenu from './AccountMenu.js';
import Button from './Button';
import {useEffect, useState} from "react";

const Header = (props) => {
    const [loggedInUserEmail, setLoggedInUserEmail] = useState("");

    useEffect(() => {
        const email = JSON.parse(localStorage.getItem('loggedInUserEmail'));
        if (email) {
            setLoggedInUserEmail(email);
        }
    },[]);

    if (localStorage.getItem("token") === null) {
        return (
            <nav>
                <Link to="/" className='logo'>
                    <img src={logo} alt="logo" width="300" />
                </Link>
                <div className='link-container'>
                    <Button size={"medium"} type={"light"} content={"Login"} onClick={() => props.setShowLoginModal(true)}></Button>
                    <Button size={"medium"} type={"dark"} content={"Sign up"} onClick={() => props.setShowRegistrationModal(true)}></Button>
                </div>
            </nav>)
    } else {
        return (
            <nav>
                <Link to="/" className='logo'>
                    <img src={logo} alt="logo" width="300" />
                </Link>
                <div className="loggedInUser">
                    <p>Welcome <strong>{loggedInUserEmail}</strong></p>
                </div>
                <div className='link-container'>
                    <AccountMenu></AccountMenu>
                </div>
            </nav>)
    }

}

export default Header;
