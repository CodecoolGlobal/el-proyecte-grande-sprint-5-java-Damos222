import React from 'react'

const AccommodationFormWrapper = (props) => {
    return (
        <>
            <h2>{props.title}</h2>
            <div className='accommodationFormWrapper'>
                {props.children}
            </div>
        </>

    )
}

export default AccommodationFormWrapper