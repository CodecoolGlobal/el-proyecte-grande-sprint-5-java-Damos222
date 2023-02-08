import '../../css/Checkout.css';
import {useNavigate} from "react-router-dom";
import {useState} from "react";

const INITIAL_DATA = {
    accommodationDto: {
        id: ""
    },
    startDate: "",
    endDate: "",
    booker: {
        id: ""
    }
}

export default function CheckoutForm({fromDate, toDate, accommodationId, accommodation}) {
    const navigate = useNavigate();
    const [data, setData] = useState(INITIAL_DATA);
    const [acc, setAcc] = useState(accommodation);

    function updateData() {
        data.startDate = fromDate;
        data.endDate = toDate;
        data.accommodationDto.id = accommodationId;
        data.booker.id = 1;
    }

    async function onSubmit(e) {
        updateData();
        uploadRest();
        navigate("/bookings/success");
    }

    async function uploadRest() {
        console.log(data);

        try {
            let res = await fetch("http://localhost:8080/bookings/checkout", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
            if (res.status === 200) {
                alert("You have successfully booked this accommodation!");
            } else {
                alert(`Error (${res.status})`);
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <>
            <div className="checkout-area">
                <h1>Checkout Page</h1>
                <div className="date-area">
                    <h3>Start Date: {fromDate}</h3>
                    <h3>End Date: {toDate}</h3>
                    <h3>Your selected accommodation:</h3>
                    <div className="details-info">
                        <span><strong>{acc.address.street} {acc.address.houseNumber}, {acc.address.zipCode} {acc.address.city}, {acc.address.country}</strong></span>
                        <p>{acc.description}</p>
                        <p>Capacity: {acc.capacity} person(s)</p>
                        <p>Type: {acc.type}</p>
                        <p>Price per night: <strong>{acc.pricePerNight} €</strong></p>
                    </div>
                </div>
                <button type="submit" onSubmit={onSubmit}>Finish</button>
            </div>
        </>
    );
}