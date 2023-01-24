import '../css/Checkout.css';


export default function Checkout({fromDate, toDate}) {

    return (
        <>
            <div className="checkout-area">
                <h1>Checkout Page</h1>
                <div className="date-area">
                    <h3>Start Date: {fromDate}</h3>
                    <h3>End Date: {toDate}</h3>
                </div>
            </div>
        </>
    );
}