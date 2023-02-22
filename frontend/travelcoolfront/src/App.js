import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import CheckoutForm from "./components/CheckoutAccommodationMultiStep/CheckoutForm";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";
import LoginForm from "./components/LoginForm";
import Modal from "./components/Modal";
import RegistrationForm from "./components/RegistrationForm";
import MyAccommodations from "./components/MyAccommodations";
import MyBookings from "./components/MyBookings";
import BookingSuccess from "./components/BookingSuccess";
import {useState} from "react";

function App() {
    const [showLoginModal, setShowLoginModal] = useState(false)
    const [showRegistrationModal, setShowRegistrationModal] = useState(false)

    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header setShowLoginModal={setShowLoginModal}
                        setShowRegistrationModal={setShowRegistrationModal} />
                    <Modal open={showLoginModal} onClose={() => setShowLoginModal(false)}>
                        <LoginForm
                            setShowLoginModal={setShowLoginModal}
                        />
                    </Modal>
                    <Modal open={showRegistrationModal} onClose={() => setShowRegistrationModal(false)}>
                        <RegistrationForm
                            setShowRegistrationModal={setShowRegistrationModal}
                            setShowLoginModal={setShowLoginModal}
                        />
                    </Modal>
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails/>}/>
                        <Route path="/bookings/checkout" exact element={<CheckoutForm/>}/>
                        <Route path="/bookings/success" exact element={<BookingSuccess/>}/>
                        <Route path="/addAccommodation" element={<AccommodationForm />} />
                        <Route path="/account/accommodations" element={<MyAccommodations setShowLoginModal={setShowLoginModal} />} />
                        <Route path="/account/bookings" element={<MyBookings />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
