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
            <h1>Accommodations</h1>
            {accommodations.map(accommodation => (
                <div className="list-accommodations">
                    <p key={accommodation.id}>{accommodation.name}</p>
                </div>
            ))}
        </div>
    );
}

export default AllAccommodations;