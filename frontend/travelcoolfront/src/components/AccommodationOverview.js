import React from 'react'
import { Link } from 'react-router-dom';
import "../css/AccommodationOverview.css"
import Button from './Button';

const AccommodationOverview = ({ accommodation }) => {
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
            <Link className="accommodation-button"
                to={"/accommodations/" + accommodation.id}>
                <Button type={"dark"} size={"large"} content={"See details"} />
            </Link>
        </div>
    );
}

export default AccommodationOverview