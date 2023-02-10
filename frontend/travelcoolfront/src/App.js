import Home from "./components/Home";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";
import Checkout from "./components/Checkout";
import { useEffect, useState } from "react";
import AccommodationForm from "./components/AddAccommodationMultiStep/AccommodationForm";
import LoginForm from "./components/LoginForm";
import Modal from "./components/Modal";
import RegistrationForm from "./components/RegistrationForm";

function App() {
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");
    const [loggedIn, setLoggedIn] = useState(false);
    const [showLoginModal, setShowLoginModal] = useState(false)
    const [showRegistrationModal, setShowRegistrationModal] = useState(false)

    useEffect(() => {
        console.log(localStorage.getItem("token"))
        setLoggedIn(checkIfTokenValid())
        console.log(loggedIn)
        return () => {
            setLoggedIn(false)
        }
    }, [setLoggedIn])

    async function checkIfTokenValid() {
        const token = localStorage.getItem("token");
        if (!token) {
            return false;
        } else {
            let res = await fetch("http://localhost:8080/auth/tokenValid?token=" + token)
            let valid = await res.text()
            console.log(valid)
            if (valid === 'true') {
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
