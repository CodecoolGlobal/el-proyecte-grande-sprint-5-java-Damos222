import {useEffect, useState} from "react";

const AllAccommodations = () => {
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
        <div className="all-accommodations">
            {accommodations.map((accommodation) => {
                const source = "data:image/jpg;base64," + accommodation.image;
                return (
                    <div className="list-accommodations" key={accommodation.id}>
                        <p key={accommodation.id}><strong>{accommodation.name}</strong></p>
                        <p key={accommodation.id}>{accommodation.description}</p>
                        <p key={accommodation.id}>Capacity: {accommodation.capacity}</p>
                        <p key={accommodation.id}>Type: {accommodation.type}</p>
                        <p key={accommodation.id}>Price per night: {accommodation.pricePerNight} </p>
                        <img src={source} style={{width: "250px"}} alt={accommodation.name + "Image"}/>
                    </div>
                );
            })}b
        </div>
    );
}
// <div className="all-accommodations">
//     <h1>Accommodations</h1>
//     {accommodations.map(accommodation => (
//         <div className="list-accommodations">
//             <p key={accommodation.id}><strong>{accommodation.name}</strong></p>
//             <p key={accommodation.id}>{accommodation.description}</p>
//             <p key={accommodation.id}>Capacity: {accommodation.capacity}</p>
//             <p key={accommodation.id}>Type: {accommodation.type}</p>
//             <p key={accommodation.id}>Price per night: {accommodation.pricePerNight} </p>
//         </div>
//     ))}
// </div>

export default AllAccommodations;