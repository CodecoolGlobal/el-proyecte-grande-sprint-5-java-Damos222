import '../css/AccommodationDetails.css';
import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import DatePicker from "react-datepicker";
import {test} from "./global";


export default function AccommodationDetails({setFromDate, setToDate}) {
    const [accommodation, setAccommodation] = useState(null);
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(addDays(5));
    const {id} = useParams();
    const navigate = useNavigate();

    function addDays(days) {
        return new Date(Date.now() + 864e5 * days);     // 864e5: number of milliseconds in a 24-hour day
    }

    function navigateToCheckout() {
        setFromDate(startDate.toLocaleDateString("en-GB"));
        setToDate(endDate.toLocaleDateString("en-GB"));
        navigate("/accommodations/checkout");
    }


    const fetchData = () => {
        fetch("http://localhost:8080/accommodations/" + id)
            .then((response) => response.json())
            .then(data => {
                setAccommodation(data);
            });
    }

    useEffect(() => {
        fetchData();
        test.data = "foo";
    }, [])

    if (accommodation == null) {
        return <h1>Loading...</h1>;
    }
    const source = "data:image/jpg;base64," + accommodation.image;
    const name = accommodation.name;

    return (
        <>
            <div className="all-accommodations">
                <h1>{name}</h1>
                <div className="accommodation-details">
                    <img src={source} style={{width: "500px"}} alt={name + "Image"}/>
                    <div className="details-info">
                        <p>{accommodation && accommodation.description}</p>
                        <p>Capacity: {accommodation && accommodation.capacity} people</p>
                        <p>Type: {accommodation && accommodation.type}</p>
                        <p>Price per night: <strong>{accommodation && accommodation.pricePerNight} €</strong></p>
                    </div>
                </div>
                <div className="reserve">
                    <h2>Book this accommodation</h2>
                    <div className="datepicker">
                        <DatePicker
                            className="start-date"
                            dateFormat="dd/MM/yyyy"
                            selected={startDate}
                            onChange={(date) => setStartDate(date)}/>
                        <DatePicker
                            className="end-date"
                            dateFormat="dd/MM/yyyy"
                            selected={endDate}
                            onChange={(date) => setEndDate(date)}/>
                    </div>

                    <div className="accommodation-button">
                        <button className="see-details" onClick={() => navigateToCheckout()}>Reserve</button>
                    </div>
                </div>
            </div>
        </>
    );
}