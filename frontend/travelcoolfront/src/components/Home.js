import '../css/Home.css';
import {useEffect, useState} from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


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
            <div className="flex-filter-box">
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
            </div>

            <AllAccommodations />

        </div>);

}

export default Home;