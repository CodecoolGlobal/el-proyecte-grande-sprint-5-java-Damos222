import {useEffect, useState} from "react";


const Home = () => {
    const [message, setMessage] = useState([]);

    const fetchData = () => {
        return fetch("http://localhost:8080/home")
            .then((response) => response.json())
            .then((data) => setMessage(data.message));
    }

    useEffect(() => {
        fetchData();
    },[])

    return (
        <div className="home">
            <h1>Book your perfect holiday!</h1>
             <p id="intro-text">{message}</p>

            <button id="journey-button">START YOUR JOURNEY</button>

        </div>
    );
}

export default Home;