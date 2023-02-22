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
    const BOOKER_ID = 1;
    const globalAccommodation = globalVars.accommodation;
    const source = "data:image/jpg;base64," + globalAccommodation.image;

    function updateData() {
        data.startDate = globalVars.startDate;
        data.endDate = globalVars.endDate;
        data.accommodationDto.id = globalVars.accommodation.id;
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
                globalVars.booking = await res.json();
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
                    <h3>Start Date: {globalVars.startDate.toLocaleDateString("en-GB")}</h3>
                    <h3>End Date: {globalVars.endDate.toLocaleDateString("en-GB")}</h3>
                    <h3>Your selected accommodation:</h3>
                    <div className="acc-info">
                        <div className="image-container">
                            <img src={source} style={{width: "280px"}} alt={globalAccommodation.name + "Image"}/>
                        </div>
                        <div>
                            <span><strong>{globalAccommodation.address.street} {globalAccommodation.address.houseNumber}, {globalAccommodation.address.zipCode} {globalAccommodation.address.city}, {globalAccommodation.address.country}</strong></span>
                            <p>{globalAccommodation.description}</p>
                            <p>Capacity: {globalAccommodation.capacity} person(s)</p>
                            <p>Type: {globalAccommodation.type}</p>
                            <p>Price per night: <strong>{globalAccommodation.pricePerNight} €</strong></p>
                        </div>
                    </div>
                </div>
                <button type="submit" className="see-details" onClick={() => onSubmit()}>Book</button>
            </div>
        </>
    );
}
