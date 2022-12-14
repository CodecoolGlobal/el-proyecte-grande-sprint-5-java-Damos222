import {useEffect, useState} from "react";

const Home = () => {

    const [title, setTitle] = useState();

    useEffect(() => {
        fetch("http://localhost:8080/home")
            .then(response => response.json())
            .then(data => setTitle(data));
    }, []);

    return (
        <div className="home">
            <h1>{title}</h1>
        </div>
    );
}

export default Home;