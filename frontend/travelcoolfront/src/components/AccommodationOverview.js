import React from 'react'
import "../css/AllAccommodations.css"

const AccommodationOverview = ({accommodation}) => {
    const source = "data:image/jpg;base64," + accommodation.image;
    return (
        <div className="list-accommodations" key={accommodation.id}>
            <div className="accommodation-image">
                <img src={source} style={{ width: "350px" }} alt={accommodation.name + "Image"} />
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
}

export default AccommodationOverview