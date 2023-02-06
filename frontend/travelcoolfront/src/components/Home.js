import '../css/Home.css';

import "react-datepicker/dist/react-datepicker.css";
import AllAccommodations from "./AllAccommodations";


export default function Home() {

    return (
        <div className="home">
            <AllAccommodations />
        </div>
    );

}