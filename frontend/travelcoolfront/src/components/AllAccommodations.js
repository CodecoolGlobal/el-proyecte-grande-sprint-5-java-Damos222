import '../css/AllAccommodations.css';
import {useEffect, useState} from "react";
import DatePicker from "react-datepicker";

export default function AllAccommodations() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(addDays(5));
    const [searchTerm, setSearchTerm] = useState("");
    const [accommodations, setAccommodations] = useState([]);

    function addDays(days) {
        return new Date(Date.now() + 864e5 * days);     // 864e5: number of milliseconds in a 24-hour day
    }
    
    const fetchAccommodations = () => {
        return fetch("http://localhost:8080/accommodations/all", {
            mode: 'cors'
        })
            .then((response) => response.json())
            .then(data => {
                setAccommodations(data);
            });
    }

    const fetchAccommodationsByDate = () => {
        return fetch("http://localhost:8080/accommodations/byDate?startDate=" + startDate.getTime() + "&endDate=" + endDate.getTime(), {
            mode: 'cors'
        })
            .then((response) => response.json())
            .then(data => {
                setAccommodations(data);
            });
    }

    useEffect(() => {
        fetchAccommodations();
    }, [])

    return (
        <>
            <div className="filter-box-label">
                <h1>Find your perfect holiday accommodation</h1>
            </div>
            <div className="flex-filter-box">
                <input
                    onChange={(event) => setSearchTerm(event.target.value.toLowerCase())}
                    className="search-input"
                    type="text"
                    placeholder="Search for country"/>
                <div className="datepicker">
                    <DatePicker
                        className="start-date"
                        dateFormat="yyyy-MM-dd"
                        selected={startDate}
                        onChange={(date) => setStartDate(date)}/>

                    <DatePicker
                        className="end-date"
                        dateFormat="yyyy-MM-dd"
                        selected={endDate}
                        onChange={(date) => setEndDate(date)}/>
                </div>
                <button id="date-button" className="see-details" onClick={() => fetchAccommodationsByDate()}>Search for date span</button>
            </div>
            <div className="all-accommodations">
                <h1>Accommodations</h1>
                {accommodations.filter((accommodation => {
                    if (searchTerm.toLowerCase() === "") {
                        return accommodation;
                    } else {
                        return accommodation.address.country.toLowerCase().includes(searchTerm);
                    }
                })).map((accommodation) => {
                    const source = "data:image/jpg;base64," + accommodation.image;
                    return (
                        <div className="list-accommodations" key={accommodation.id}>
                            <div className="accommodation-image">
                                <img src={source} style={{width: "350px"}} alt={accommodation.name + "Image"}/>
                            </div>
                            <div className="accommodation-info">
                                <p><strong>{accommodation.name}</strong></p>
                                <span>{accommodation.address.street} {accommodation.address.houseNumber}, {accommodation.address.zipCode} {accommodation.address.city}, {accommodation.address.country}</span>
                                <p>{accommodation.description}</p>
                                <p>Capacity: {accommodation.capacity} person(s)</p>
                                <p>Type: {accommodation.type}</p>
                                <p>
                                    Price per night: <strong>{accommodation.pricePerNight} €</strong>
                                </p>
                            </div>
                            <div className="accommodation-button">
                                <a href={"http://localhost:3000/accommodations/" + accommodation.id}>
                                    <button className="see-details">See details</button>
                                </a>
                            </div>
                        </div>
                    );
                })}
            </div>
        </>
    );
}