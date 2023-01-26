import React from 'react'
import '../css/FormWrapper.css'

const FormWrapper = (props) => {
    return (
        <>
            <h2>{props.title}</h2>
            <div className='formWrapper'>
                {props.children}
            </div>
        </>

    )
}

export default FormWrapper