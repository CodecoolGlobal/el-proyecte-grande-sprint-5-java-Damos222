import '../../css/Checkout.css';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import useMultipartForm from "../useMultistepForm";
import AddressForm from "../AddAccommodationMultiStep/AddressForm";
import AccommodationDetailsForm from "../AddAccommodationMultiStep/AccommodationDetailsForm";
import FeaturesForm from "../AddAccommodationMultiStep/FeaturesForm";
import OverviewAndConfirm from "../AddAccommodationMultiStep/OverviewAndConfirm";


export default function CheckoutForm({fromDate, toDate}) {
    const navigate = useNavigate();
    const [data, setData] = useState();


    function updateData(newInput) {
        setData(prev => {
            return {...prev, ...newInput}
        })
    }

    const {steps, currentStepIndex, step, isFirstStep, isLastStep, back, next} = useMultipartForm([
        <AddressForm data={data} updateData={updateData}></AddressForm>,
        <AccommodationDetailsForm data={data} updateData={updateData}></AccommodationDetailsForm>,
        <FeaturesForm data={data} updateData={updateData}></FeaturesForm>,
        <OverviewAndConfirm data={data} updateData={updateData}></OverviewAndConfirm>
    ])

    async function onSubmit(e) {
        e.preventDefault();
        if (!isLastStep) {
            return next();
        }
        uploadRest();
        navigate("/");
    }

    async function uploadRest() {
        console.log(data);

        try {
            let res = await fetch("http://localhost:8080/accommodations/add", {
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

                <div className='multiPartFormContainer'>
                    <form onSubmit={onSubmit}>
                        <div className='progressIndicator'>
                            {currentStepIndex + 1} / {steps.length}
                        </div>
                        {step}
                        <div className='multipartFormButtonContainer'>
                            {!isFirstStep && (<button type='button' onClick={back}>Back</button>)}
                            <button type='submit'>{isLastStep ? 'Finish' : 'Next'}</button>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}