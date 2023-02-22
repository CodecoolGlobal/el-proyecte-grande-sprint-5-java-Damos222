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
import BookingSuccess from "./components/BookingSuccess";
import {useEffect, useState} from "react";

function App() {
    const [loggedIn, setLoggedIn] = useState(false);
    const [showLoginModal, setShowLoginModal] = useState(false);
    const [showRegistrationModal, setShowRegistrationModal] = useState(false);

    useEffect(() => {
        setLoggedIn(checkIfTokenValid())
        return () => {
            setLoggedIn(false)
        }
    }, [])

    async function checkIfTokenValid() {
        const token = localStorage.getItem("token");
        if (token === null) {
            return false;
        } else {
            let res = await fetch("http://localhost:8080/auth/tokenValid?token=" + token)
            let valid = await res.text()
            return valid === 'true';
        }
    }

    return (
        <Router>
            <div className="App">
                <div className="content">
                    <Header setShowLoginModal={setShowLoginModal}
                        setShowRegistrationModal={setShowRegistrationModal} />
                    <Modal open={showLoginModal} onClose={() => setShowLoginModal(false)}>
                        <LoginForm
                            setLoggedIn={setLoggedIn}
                            setShowLoginModal={setShowLoginModal}
                            loggedIn={loggedIn}
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
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
