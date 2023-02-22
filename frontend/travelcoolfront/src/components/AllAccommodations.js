import '../css/AllAccommodations.css';
import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import AccommodationOverview from './AccommodationOverview';

export default function AllAccommodations() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(addDays(5));
    const [searchTerm, setSearchTerm] = useState("");
    const [accommodations, setAccommodations] = useState([]);
    const [price, setPrice] = useState("10000");
    const [capacity, setCapacity] = useState(0);


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

    const incrementCount = () => {
        setCapacity(capacity + 1);
        console.log(capacity)
    }
    const decrementCount = () => {
        setCapacity(capacity - 1);
        console.log(capacity)
    }

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
                    placeholder="Search for country" />
                <div className="datepicker">
                    <DatePicker
                        className="start-date"
                        dateFormat="yyyy-MM-dd"
                        selected={startDate}
                        onChange={(date) => setStartDate(date)} />

                    <DatePicker
                        className="end-date"
                        dateFormat="yyyy-MM-dd"
                        selected={endDate}
                        onChange={(date) => setEndDate(date)} />
                </div>
                <input
                    className="max-price-per-night"
                    onChange={(e) => setPrice(e.target.value)}

                    type="text"
                    placeholder="max. price per night"
                />
                <button
                    className="add-substract-button"
                    onClick={decrementCount}
                >-</button>
                <input
                    className="capacity"
                    value={capacity.toString()}
                    onChange={(e) => setCapacity(parseInt(e.target.value))}
                />

                <button
                    className="add-substract-button"
                    onClick={incrementCount}
                >+</button>


                <button id="date-button" className="see-details" onClick={() => fetchAccommodationsByDate()}>Search for date span</button>
            </div>


            <div className="all-accommodations">
                <h1>Accommodations</h1>
                {accommodations.filter((accommodation => {
                    if (searchTerm.toLowerCase() === "") {

                        if (capacity === 0) {
                            return accommodation.pricePerNight <= parseInt(price, 10);
                        }

                        return accommodation.pricePerNight <= parseInt(price, 10)
                            && accommodation.capacity === capacity;
                    } else {

                        return (
                            accommodation.address.country.toLowerCase().includes(searchTerm)
                            || accommodation.address.city.toLowerCase().includes(searchTerm))
                            && accommodation.pricePerNight <= parseInt(price, 10)
                            && accommodation.capacity === capacity;
                    }



                })).map((accommodation) => {
                    return (
                        <div key={accommodation.id}>
                            <AccommodationOverview key={accommodation.key} accommodation={accommodation}></AccommodationOverview>
                        </div>
                    );
                })}
            </div>
        </>
    );
}