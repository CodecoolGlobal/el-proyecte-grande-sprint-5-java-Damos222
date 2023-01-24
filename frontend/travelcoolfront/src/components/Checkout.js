import '../css/Checkout.css';


export default function Checkout({fromDate, toDate}) {

    return (
        <>
            <h2>Start Date: {fromDate}</h2>
            <h2>End Date: {toDate}</h2>
        </>
    );
}