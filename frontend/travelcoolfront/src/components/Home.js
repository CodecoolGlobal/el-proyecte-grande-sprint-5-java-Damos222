import '../css/Home.css';
import {useState} from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [search, setSearch] = useState("");

    return (
        <div className="home">
            <div className="filter-box-label">
                <h1>Find your perfect holiday accommodation</h1>
            </div>
            <div className="flex-filter-box">
                <input
                    onChange={(event) => setSearch(event.target.value)}
                    className="search-input"
                    type="text"
                    placeholder="Search"/>
                <div className="datepicker">
                    <DatePicker
                        className="start-date"
                        dateFormat="dd/MM/yyyy"
                        selected={startDate}
                        onChange={(date) => setStartDate(date)}/>

                    <DatePicker
                        className="end-date"
                        dateFormat="dd/MM/yyyy"
                        selected={endDate}
                        onChange={(date) => setEndDate(date)}/>
                </div>
            </div>
            <AllAccommodations search={search}/>
        </div>
    );

}