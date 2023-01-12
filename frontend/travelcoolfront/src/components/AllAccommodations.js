import {useEffect, useState} from "react";

export default function AllAccommodations() {
    const [accommodations, setAccommodations] = useState([]);

    const fetchData = () => {
        return fetch("http://localhost:8080/accommodation/all")
            .then((response) => response.json())
            .then(data => {
                setAccommodations(data);
            })
    }
    useEffect(() => {
        fetchData();
    }, [])


    return (
        <>
            <div className="all-accommodations">
                <h1>All Accommodations</h1>
                {accommodations.map((accommodation) => {
                    const source = "data:image/jpg;base64," + accommodation.image;
                    return (
                        <div className="list-accommodations" key={accommodation.id}>
                            <div className="accommodation-image">
                                <img src={source} style={{width: "250px"}} alt={accommodation.name + "Image"}/>
                            </div>
                            <div className="accommodation-info">
                                <p><strong>{accommodation.name}</strong></p>
                                <p>{accommodation.description}</p>
                                <p>Capacity: {accommodation.capacity} people</p>
                                <p>Type: {accommodation.type}</p>
                                <p>
                                    Price per night: <strong>{accommodation.pricePerNight} €</strong>
                                </p>
                            </div>
                            <div className="accommodation-button">
                                <a href={"http://localhost:3000/accommodation/" + accommodation.id}>
                                    <button className="journey-button">See details</button>
                                </a>
                            </div>
                        </div>
                    );
                })}
            </div>
        </>
    );
}