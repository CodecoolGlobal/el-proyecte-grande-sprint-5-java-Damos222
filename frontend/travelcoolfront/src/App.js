import Home from "./components/Home";
import Header from "./components/Header";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import Checkout from "./components/Checkout";
import {useState} from "react";

function App() {
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");

    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header/>
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails setFromDate={setFromDate} setToDate={setToDate}/>}/>
                        <Route path="/accommodations/checkout" exact element={<Checkout fromDate={fromDate} toDate={toDate}/>}/>
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
