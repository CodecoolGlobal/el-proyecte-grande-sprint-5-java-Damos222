import '../css/Home.css';
import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home() {

    return (
        <div className="home">
            <AllAccommodations />
        </div>
    );
}
