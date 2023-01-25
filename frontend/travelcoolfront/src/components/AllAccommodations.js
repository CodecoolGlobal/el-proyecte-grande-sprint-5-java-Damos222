import '../css/AllAccommodations.css';
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

export default function AllAccommodations({search, startDate, endDate}) {
    const [accommodations, setAccommodations] = useState([]);
    const [bookings, setBookings] = useState([]);
    console.log(startDate);
    console.log(endDate);

    const fetchAccommodations = () => {
        return fetch("http://localhost:8080/accommodations/all")
            .then((response) => response.json())
            .then(data => {
                setAccommodations(data);
            })
    }

    const fetchBookings = () => {
        return fetch("http://localhost:8080/bookings/all")
            .then((response) => response.json())
            .then(data => {
                setBookings(data);
            })
    }

    useEffect(() => {
        fetchAccommodations();
        fetchBookings();
    }, [])

    return (
        <>
            <div className="all-accommodations">
                <h1>Accommodations</h1>
                {accommodations.filter((accommodation => {
                    if (search.toLowerCase() === "") {
                        return accommodation;
                    } else {
                        return accommodation.address.country.toLowerCase().includes(search);
                    }
                })).map((accommodation) => {
                    const source = "data:image/jpg;base64," + accommodation.image;
                    return (
                        <div className="list-accommodations" key={accommodation.id}>
                            <div className="accommodation-image">
                                <img src={source} style={{width: "350px"}} alt={accommodation.name + "Image"}/>
                            </div>
                            <div className="accommodation-info">
                                <p><strong>{accommodation.name}</strong></p>
                                <span>{accommodation.address.street} {accommodation.address.houseNumber}, {accommodation.address.zipCode} {accommodation.address.city}, {accommodation.address.country}</span>
                                <p>{accommodation.description}</p>
                                <p>Capacity: {accommodation.capacity} person(s)</p>
                                <p>Type: {accommodation.type}</p>
                                <p>
                                    Price per night: <strong>{accommodation.pricePerNight} €</strong>
                                </p>
                            </div>
                            <div className="accommodation-button">
                                <a href={"http://localhost:3000/accommodations/" + accommodation.id}>
                                    <button className="see-details">See details</button>
                                </a>
                            </div>
                        </div>
                    );
                })}
            </div>
        </>
    );
}