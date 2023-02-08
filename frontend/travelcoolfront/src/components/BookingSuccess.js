import {useEffect, useState} from "react";

export default function BookingSuccess() {
    const [successMessage, setSuccessMessage] = useState("");

    const fetchSuccessMessage = () => {
        fetch("http://localhost:8080/bookings/success")
            .then(response => response.json())
            .then(data => {
                setSuccessMessage(data)
            });
    }

    useEffect( () => {
        fetchSuccessMessage();
    }, []);

    return(
      <>
        <h1>{successMessage}</h1>
      </>
    );
}