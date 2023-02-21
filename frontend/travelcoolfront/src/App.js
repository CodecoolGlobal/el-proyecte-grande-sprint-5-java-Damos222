import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import CheckoutForm from "./components/CheckoutAccommodationMultiStep/CheckoutForm";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";
import BookingSuccess from "./components/BookingSuccess";

function App() {

    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header />
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails/>}/>
                        <Route path="/bookings/checkout" exact element={<CheckoutForm/>}/>
                        <Route path="/bookings/success" exact element={<BookingSuccess/>}/>
                        <Route path="/addAccommodation" element={<AccommodationForm />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
