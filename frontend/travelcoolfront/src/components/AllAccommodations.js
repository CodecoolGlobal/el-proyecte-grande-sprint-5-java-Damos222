import '../css/AllAccommodations.css';
import {useEffect, useState} from "react";

export default function AllAccommodations({search}) {
    const [accommodations, setAccommodations] = useState([]);

    const fetchData = () => {
        return fetch("http://localhost:8080/accommodations/all")
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
                {accommodations.filter((accommodation => {
                    return search.toLowerCase() === ""
                        ? accommodation
                        : accommodation.name.toLowerCase().includes(search)
                })).map((accommodation) => {
                    const source = "data:image/jpg;base64," + accommodation.image;
                    return (
                        <div className="list-accommodations" key={accommodation.id}>
                            <div className="accommodation-image">
                                <img src={source} style={{width: "250px"}} alt={accommodation.name + "Image"}/>
                            </div>
                            <div className="accommodation-info">
                                <p><strong>{accommodation.name}</strong></p>
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