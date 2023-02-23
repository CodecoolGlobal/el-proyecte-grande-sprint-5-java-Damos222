import '../css/AccommodationDetails.css';
import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {globalVars} from "../global/globalVars";

export default function AccommodationDetails() {
    const [accommodation, setAccommodation] = useState(null);
    const {id} = useParams();
    const navigate = useNavigate();

    const fetchData = () => {
        fetch("http://localhost:8080/accommodations/public/" + id)
            .then((response) => response.json())
            .then(data => {
                setAccommodation(data);
            });
    }

    function goToCheckout() {
        globalVars.accommodation = accommodation;
        navigate("/bookings/checkout");
    }

    useEffect(() => {
        fetchData();
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
                        <span><strong>{accommodation.address.street} {accommodation.address.houseNumber}, {accommodation.address.zipCode} {accommodation.address.city}, {accommodation.address.country}</strong></span>
                        <p>{accommodation && accommodation.description}</p>
                        <p>Capacity: {accommodation && accommodation.capacity} person(s)</p>
                        <p>Type: {accommodation && accommodation.type}</p>
                        <p>Price per night: <strong>{accommodation && accommodation.pricePerNight} €</strong></p>
                    </div>
                </div>
                <div className="reserve">
                    <h2>Book this accommodation</h2>
                    <span>{globalVars.startDate.toLocaleDateString("en-GB")} - {globalVars.endDate.toLocaleDateString("en-GB")}</span>
                    <button id="book-button" className="see-details" onClick={() => goToCheckout()}>Reserve</button>
                </div>
            </div>
        </>
    );
}
