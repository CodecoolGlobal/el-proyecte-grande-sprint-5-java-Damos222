import '../css/Home.css';

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home({setFromDate, setToDate}) {

    return (
        <div className="home">
            <AllAccommodations
                setFromDate={setFromDate}
                setToDate={setToDate}/>
        </div>
    );

}
