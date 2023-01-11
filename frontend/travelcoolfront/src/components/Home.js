import {useEffect, useState} from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";


const Home = () => {
    const [message, setMessage] = useState([]);

    const [startDate, setStartDate] = useState(new Date());
    const fetchData = () => {
        return fetch("http://localhost:8080/home")
            .then((response) => response.json())
            .then(data => data.message)
            .then(message => {
                message = message.split("\n")
                setMessage(message);
            })
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (

        <div className="home">
            <div className="combo-box-1">
                <div><input
                    className="search-input-1"
                    type="text"
                    placeholder="Search"/></div>

                <div><DatePicker
                    className="start-date"
                    selected={startDate}
                    onChange={(date) => setStartDate(date)}/>
                </div>
                <div><DatePicker
                    className="end-date"
                    selected={startDate}
                    onChange={(date) => setStartDate(date)}/></div>

            </div>


            <div className="travel-suggestions">
                <div className="suggestions-text">
                    <h1 className="book-your-perfect-holiday">Book your perfect holiday!</h1>
                    <p className="intro-text">
                        {message.map(line => {
                            return (<>{line}
                                <br/>
                                test
                            </>)
                        })}
                    </p>

                    <button className="journey-button">START YOUR JOURNEY</button>
                </div>
                <div className="suggestions-image">
                    <img src="/images/maldives.jpg" width="640" alt="Beach in the Maldives"/>
                </div>
            </div>

        </div>);

}

export default Home;