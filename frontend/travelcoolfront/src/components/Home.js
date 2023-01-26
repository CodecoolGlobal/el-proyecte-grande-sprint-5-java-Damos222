import '../css/Home.css';
import {useState} from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


const Home = () => {
    const [startDate, setStartDate] = useState(new Date());

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