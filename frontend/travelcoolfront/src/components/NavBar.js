import logo from "../assets/logo.png";

const NavBar = () => {
    const registration = "/registration";
    const login = "/login";

    return (<nav className="navbar">
            <div>
                <img src={logo} alt="logo" width="350"/>
            </div>
            <div className="links">

                <a href={registration} >
                    <button role="link"
                            id="sign-up-button"
                    >Sign up</button>
                </a>

                <a href={login} >
                    <button role="link"
                    id="log-in-button">Log in</button>
                </a>
            </div>
        </nav>);
}

export default NavBar;