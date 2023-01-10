import {useEffect} from "react";

const AllAccommodations = () => {
    // const [accommodations, setAccommodations] = useState();

    const fetchData = () => {
        return fetch("http://localhost:8080/accommodations")
            .then((response) => response.json())
            .then(data => data.message)
            .then(accommodations => {
                console.log(accommodations);
            })
    }
    useEffect(() => {
        fetchData();
    }, [])


    return (
        <div>
            <h1>Accommodations</h1>
        </div>
    );
}

export default AllAccommodations;