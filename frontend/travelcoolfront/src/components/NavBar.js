import {useEffect, useState} from "react";

const NavBar = () => {
    const registration = "/registration";
    const login = "/login";

    const [title, setTitle] = useState();

    useEffect(() => {
        async function fetchData() {
            const response = await fetch("http://localhost:8080/home");
            const data = await response.json();
            setTitle(data);
        }
        fetchData();
    }, []);

    return (
        <nav className="navbar">
            <h1>{title}</h1>
            <div className="links">
                <a href={registration}>Registration</a>
                <a href={login}>Login</a>
            </div>
        </nav>
    );
}

export default NavBar;