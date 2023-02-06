import React from "react"
import FormWrapper from "../FormWrapper"
import Gallery from "../Gallery"

// takes in files and corresponding images each in an array; order must be the same.

const OverviewAndConfirm = (props) => {
    return (
        <div>
            <FormWrapper title="Overview and confirm" />
            {Object.keys(props.data).map((key) => {
                return <p key={key}>{key.charAt(0).toUpperCase() + key.slice(1) + ": " + props.data[key]}</p>
            })}
            <Gallery images={props.images} files={props.files}></Gallery>
        </div>
        
    )
}

export default OverviewAndConfirm