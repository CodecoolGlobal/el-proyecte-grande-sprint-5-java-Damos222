import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import Checkout from "./components/Checkout";
import {useEffect, useState} from "react";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";

function App() {
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");
    const [loggedIn, setLoggedIn] = useState(checkIfTokenValid());

    async function checkIfTokenValid() {
        const token = localStorage.getItem("token");
        if (!token) {
            return false;
        } else {
            let res = await fetch("http://localhost:8080/api/v1/auth/checkIfTokenValid?token=" + token)
            let json = res.json()
            if (json.valid) {
                return true;
            } else {
                return false;
            }
        }
    }



    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header setLoggedIn={setLoggedIn}/>
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails setFromDate={setFromDate} setToDate={setToDate}/>}/>
                        <Route path="/accommodations/checkout" exact element={<Checkout fromDate={fromDate} toDate={toDate}/>}/>
                        <Route path="/addAccommodation" element={<AccommodationForm />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
