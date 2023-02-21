import '../../css/Checkout.css';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {globalVars} from "../../global/globalVars";

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

export default function CheckoutForm() {
    const navigate = useNavigate();
    const [data] = useState(INITIAL_DATA);
    const [accommodation] = useState(globalVars.accommodation);
    const BOOKER_ID = 1;

    function updateData() {
        data.startDate = globalVars.startDate;
        data.endDate = globalVars.endDate;
        data.accommodationDto.id = globalVars.accommodationId;
        data.booker.id = BOOKER_ID;
    }

    async function onSubmit() {
        updateData();
        await uploadRest();
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
                    <h3>Start Date: {globalVars.startDate.toLocaleDateString("en-GB")} End Date: {globalVars.endDate.toLocaleDateString("en-GB")}</h3>
                    <h3>Your selected accommodation:</h3>
                    <div className="details-info">
                        {/*<span><strong>{accommodation.address.street} {accommodation.address.houseNumber}, {accommodation.address.zipCode} {accommodation.address.city}, {accommodation.address.country}</strong></span>*/}
                        {/*<p>{accommodation.description}</p>*/}
                        {/*<p>Capacity: {accommodation.capacity} person(s)</p>*/}
                        {/*<p>Type: {accommodation.type}</p>*/}
                        {/*<p>Price per night: <strong>{accommodation.pricePerNight} €</strong></p>*/}
                    </div>
                </div>
                <button type="submit" onSubmit={onSubmit}>Finish</button>
            </div>
        </>
    );
}
