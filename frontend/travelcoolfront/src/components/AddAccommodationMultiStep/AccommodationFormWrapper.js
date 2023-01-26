import React from 'react'
import '../../css/FormWrapper.css'

const AccommodationFormWrapper = (props) => {
    return (
        <>
            <h2>{props.title}</h2>
            <div className='formWrapper'>
                {props.children}
            </div>
        </>

    )
}

export default AccommodationFormWrapper