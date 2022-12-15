import {useEffect, useState} from "react";


const Home = () => {
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
        <div className="home">
            <h1>TravelCool</h1>
            <h2>{title}</h2>
        </div>
    );
}

export default Home;