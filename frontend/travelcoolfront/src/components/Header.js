import '../css/Header.css';
import logo from "../assets/logo.png";
import { Link } from "react-router-dom";
import { useEffect, useRef } from 'react';
import PositionedMenu from './PositionedMenu.js';
import jwtDecode from 'jwt-decode';

const Header = (props) => {
    const email = useRef(null)

    useEffect(() => {
        let userToken = localStorage.getItem("token")
        console.log(userToken)
        const userName = userToken ? jwtDecode(userToken).email : null;
        console.log(userName)
        getEmail()
        console.log("Email: " + email.current)
        return () => {
            ""
        }
    }, [email])

    async function getEmail() {
        fetch("http://localhost:8080/account/email", {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then(res => res.text())
            .then(text => {
                console.log(text)
                email.current = text
            })
    }


    if (localStorage.getItem("token") === null) {
        return (
            <nav>
                <Link to="/" className='logo'>
                    <img src={logo} alt="logo" width="300" />
                </Link>
                <div className='link-container'>
                    <button className="light link" onClick={() => props.setShowLoginModal(true)}>Login</button>
                    <button className="dark link" onClick={() => props.setShowRegistrationModal(true)}>Sign Up</button>
                </div>
            </nav>)
    } else {
        return (
            <nav>
                <Link to="/" className='logo'>
                    <img src={logo} alt="logo" width="300" />
                </Link>
                <div className='link-container'>
                    <PositionedMenu></PositionedMenu>
                </div>
            </nav>)
    }

}

export default Header;