import '../css/Home.css';
import {useState} from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());

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
                        dateFormat="dd/MM/yyyy"
                        selected={startDate}
                        onChange={(date) => setStartDate(date)}/>
                    </div>
                    <div><DatePicker
                        className="end-date"
                        dateFormat="dd/MM/yyyy"
                        selected={endDate}
                        onChange={(date) => setEndDate(date)}/></div>
                </div>
            </div>
            <AllAccommodations/>
        </div>
    );

}