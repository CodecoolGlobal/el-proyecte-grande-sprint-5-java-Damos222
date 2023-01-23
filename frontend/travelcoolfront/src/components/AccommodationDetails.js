import '../css/AccommodationDetails.css';
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

export default function AccommodationDetails() {
    const [accommodation, setAccommodation] = useState(null);
    const {id} = useParams();

    const fetchData = () => {
        fetch("http://localhost:8080/accommodations/" + id)
            .then((response) => response.json())
            .then(data => {
                setAccommodation(data);
            });
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
                        <p>{accommodation && accommodation.description}</p>
                        <p>Capacity: {accommodation && accommodation.capacity} people</p>
                        <p>Type: {accommodation && accommodation.type}</p>
                        <p>Price per night: <strong>{accommodation && accommodation.pricePerNight} €</strong></p>
                    </div>
                </div>
                <div className="accommodation-details">
                    <div className="details-features">
                        <p>Features</p>
                    </div>
                </div>
            </div>
        </>
    );
}