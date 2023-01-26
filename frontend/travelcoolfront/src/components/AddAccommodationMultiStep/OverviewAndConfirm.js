import React from "react"
import AccommodationFormWrapper from "./AccommodationFormWrapper"

const OverviewAndConfirm = (props) => {
    return (
        <div>
            <AccommodationFormWrapper title="Overview and confirm" />
            {Object.keys(props.data).map((key) => {
                return <p key={key}>{key.charAt(0).toUpperCase() + key.slice(1) + ": " + props.data[key]}</p>
            })}
        </div>
    )
}

export default OverviewAndConfirm