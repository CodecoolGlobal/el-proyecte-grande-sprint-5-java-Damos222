import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import Checkout from "./components/Checkout";
import { useState } from "react";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";
import LoginForm from "./components/LoginForm";
import Modal from "./components/Modal";
import RegistrationForm from "./components/RegistrationForm";

function App() {
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");
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
                        <Route path="/" exact element={<Home />} />
                        <Route path="/accommodations/all" exact element={<AllAccommodations />} />
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails setFromDate={setFromDate} setToDate={setToDate} />} />
                        <Route path="/accommodations/checkout" exact element={<Checkout fromDate={fromDate} toDate={toDate} />} />
                        <Route path="/addAccommodation" element={<AccommodationForm />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
