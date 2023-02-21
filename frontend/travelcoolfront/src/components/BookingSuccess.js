import {globalVars} from "../global/globalVars";

export default function BookingSuccess() {

    return(
      <>
        <h1>You have successfully booked your accommodation {globalVars.booking.accommodationDto.title}!</h1>
      </>
    );
}
