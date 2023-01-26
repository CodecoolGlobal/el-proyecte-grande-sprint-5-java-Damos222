import React from "react"
import FormWrapper from "../FormWrapper"

const OverviewAndConfirm = (props) => {
    return (
        <div>
            <FormWrapper title="Overview and confirm" />
            {Object.keys(props.data).map((key) => {
                return <p key={key}>{key.charAt(0).toUpperCase() + key.slice(1) + ": " + props.data[key]}</p>
            })}
        </div>
    )
}

export default OverviewAndConfirm