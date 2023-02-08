import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import CheckoutForm from "./components/CheckoutAccommodationMultiStep/CheckoutForm";
import {useState} from "react";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";
import BookingSuccess from "./components/BookingSuccess";

function App() {
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");
    const [accommodationId, setAccommodationId] = useState(null);
    const [accommodation, setAccommodation] = useState(null);

    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header />
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails
                            setFromDate={setFromDate}
                            setToDate={setToDate}
                            setAccommodationId={setAccommodationId}
                            setAccommodation={setAccommodation}/>}/>
                        <Route path="/bookings/checkout" exact element={<CheckoutForm
                            fromDate={fromDate}
                            toDate={toDate}
                            accommodationId={accommodationId}
                            accommodation={accommodation}/>}/>
                        <Route path="/bookings/success" exact element={<BookingSuccess/>}/>
                        <Route path="/addAccommodation" element={<AccommodationForm />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
