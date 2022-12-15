import Logo from "../assests/TravelCool-Logo.png";

const NavBar = () => {
    const registration = "/registration";
    const login = "/login";

    return (<nav className="navbar">
            <div>
                <img src={Logo} alt="logo" width="300"/>
            </div>
            <div className="links">
                <a href={registration}>Registration</a>
                <a href={login}>Login</a>
            </div>


        </nav>);
}

export default NavBar;