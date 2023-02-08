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

export default function CheckoutForm({fromDate, toDate, accommodationId}) {
    const navigate = useNavigate();
    const [data, setData] = useState(INITIAL_DATA);


    async function onSubmit(e) {
        e.preventDefault();
        uploadRest();
        navigate("/bookings/success");
    }

    async function uploadRest() {
        console.log(data);

        try {
            let res = await fetch("http://localhost:8080/bookings/checkout", {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                }
            })
            if (res.status === 200) {
                alert("You successfully published your accommodation!");
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
                </div>
                <button type=submit onSubmit={onSubmit}>Finish</button>
            </div>
        </>
    );
}