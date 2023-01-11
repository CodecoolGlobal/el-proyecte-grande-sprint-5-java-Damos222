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
        <div>
            <h1>Accommodations</h1>
            {accommodations.map(accommodation => (
                <div>
                    <p key={accommodation.id}>{accommodation.name}</p>
                </div>
            ))}
        </div>
    );
}

export default AllAccommodations;