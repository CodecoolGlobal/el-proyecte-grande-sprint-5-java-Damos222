import '../css/Home.css';
import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(addDays(5));
    const [search, setSearch] = useState("");

    function addDays(days) {
        return new Date(Date.now() + 864e5 * days);     // 864e5: number of milliseconds in a 24-hour day
    }

    useEffect(() => {
    }, [startDate, endDate])

    return (
        <div className="home">
            <div className="filter-box-label">
                <h1>Find your perfect holiday accommodation</h1>
            </div>
            <div className="flex-filter-box">
                <input
                    onChange={(event) => setSearch(event.target.value.toLowerCase())}
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
                <button id="date-button" className="see-details">Search for date span</button>
            </div>
            <AllAccommodations search={search}
                startDate={startDate.toLocaleDateString('en-CA')}
                endDate={endDate.toLocaleDateString('en-CA')} />
        </div>
    );
}
