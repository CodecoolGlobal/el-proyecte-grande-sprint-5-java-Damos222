import React from 'react'
import { useState } from 'react'

import useMultipartForm from '../useMultistepForm'
import AccommodationDetailsForm from './AccommodationDetailsForm'
import AddressForm from './AddressForm'
import FeaturesForm from './FeaturesForm'
import FotosForm from './FotosForm'
import OverviewAndConfirm from './OverviewAndConfirm'
import TitleForm from './TitleForm'

const AccommodationForm = () => {
    const [data, setData] = useState(new FormData())

    function updateData(fields) {
        setData(prev => {
            return { ...prev, ...fields }
        })
    }

    const { steps, currentStepIndex, step, isFirstStep, isLastStep, back, next } = useMultipartForm([
        <TitleForm data={data} updateData={updateData}></TitleForm>,
        <AddressForm data={data} updateData={updateData}></AddressForm>,
        <AccommodationDetailsForm data={data} updateData={updateData}></AccommodationDetailsForm>,
        <FotosForm data={data} updateData={updateData}></FotosForm>,
        <FeaturesForm data={data} updateData={updateData}></FeaturesForm>,
        <OverviewAndConfirm data={data}></OverviewAndConfirm>
    ])

    function onSubmit(e) {
        e.preventDefault()
        if (!isLastStep) return next()
        console.log(data)
        alert("You successfully published your accommodation!")
    }


    return (
        <div className='multiPartForm'>
            <form onSubmit={onSubmit}>
                <div className='progressIndicator'>
                    {currentStepIndex + 1} / {steps.length}
                </div>
                {step}
                <div className='multipartFormButtonContainer'>
                    {!isFirstStep && (<button type='button' onClick={back}>Back</button>)}
                    <button type='submit'>{isLastStep ? 'Finish' : 'Next'}</button>
                </div>
            </form>
        </div>
    )
}

export default AccommodationForm