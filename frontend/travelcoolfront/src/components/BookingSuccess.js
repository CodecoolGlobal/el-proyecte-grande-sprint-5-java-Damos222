import {globalVars} from "../global/globalVars";
import '../css/BookingSuccess.css';
import {Link} from "react-router-dom";

export default function BookingSuccess() {

    return (
        <>
            <div className="success-area">
                <h1>You have successfully booked your accommodation "{globalVars.booking.accommodationDto.title}"!</h1>
                <Link className="success-button see-details" to="/">Back to All Accommodations</Link>
            </div>
        </>
    );
}
