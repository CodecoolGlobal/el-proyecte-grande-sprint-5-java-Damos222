import Home from "./components/Home";
import NavBar from "./components/NavBar";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import AllAccommodations from "./components/AllAccommodations";
import AccommodationDetails from "./components/AccommodationDetails";

function App() {
    return (
        <Router>
            <div className="App">
                <div className="content">
                    <NavBar/>
                    <Routes>
                        <Route path="/" exact element={<Home/>}/>
                        <Route path="/accommodations/all" exact element={<AllAccommodations/>}/>
                        <Route path="/accommodations/:id" exact element={<AccommodationDetails/>}/>
                    </Routes>
                </div>
                <p> Test</p>
            </div>
        </Router>
    );
}

export default App;
