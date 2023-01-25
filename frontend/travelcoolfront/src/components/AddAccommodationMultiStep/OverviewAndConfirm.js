import React from "react"

const OverviewAndConfirm = (props) => {
    return (
        <div>
            <img img={props.data.images[0]} alt=''></img>
            {Object.keys(props.data).map((key) => {
                return <p key={key}>{key.charAt(0).toUpperCase() + key.slice(1) + ": " + props.data[key]}</p>
            })}
        </div>
    )
}

export default OverviewAndConfirm