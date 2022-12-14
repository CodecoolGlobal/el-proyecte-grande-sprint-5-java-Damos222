const NavBar = () => {
    const registration = "/registration";
    const login = "/login";

    return (
        <nav className="navbar">
            <h1>TravelCool</h1>
            <div className="links">
                <a href={registration}>Registration</a>
                <a href={login}>Login</a>
            </div>
        </nav>
    );
}

export default NavBar;