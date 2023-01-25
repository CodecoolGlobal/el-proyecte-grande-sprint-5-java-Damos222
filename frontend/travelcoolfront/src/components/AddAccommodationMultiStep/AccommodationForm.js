import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

import useMultipartForm from '../useMultistepForm'
import AccommodationDetailsForm from './AccommodationDetailsForm'
import AddressForm from './AddressForm'
import FeaturesForm from './FeaturesForm'
import FotosForm from './FotosForm'
import OverviewAndConfirm from './OverviewAndConfirm'
import TitleForm from './TitleForm'

const AccommodationForm = () => {
    const navigate = useNavigate()
    const [images, setImages] = useState([])
    const [data, setData] = useState(new FormData())
    const FOTOS_FORM_INDEX = 1

    function updateImages(newFiles) {
        const newImages = []
        for (let file of newFiles) {
            newImages.push(URL.createObjectURL(file))
        }
        setImages((prev => {
            return [...prev, ...newImages]
        }))
    }

    function fuseOldAndNewImages(fields) {
        let imageList = new DataTransfer()
        for (let file of fields.images) {
            imageList.items.add(file)
        }
        for (let file of data.images) {
            imageList.items.add(file)
        }
        return { images: imageList.files }
    }

    function updateData(fields) {
        if (fields.images && data.images) {
            fields = fuseOldAndNewImages(fields)
        }
        setData(prev => {
            return { ...prev, ...fields }
        })
        console.log("data: ", data)
    }

    const { steps, currentStepIndex, step, isFirstStep, isLastStep, back, next } = useMultipartForm([
        <FotosForm data={data} updateData={updateData} updateImages={updateImages} images={images} ></FotosForm>,
        <TitleForm data={data} updateData={updateData}></TitleForm>,
        <AddressForm data={data} updateData={updateData}></AddressForm>,
        <AccommodationDetailsForm data={data} updateData={updateData}></AccommodationDetailsForm>,
        <FeaturesForm data={data} updateData={updateData}></FeaturesForm>,
        <OverviewAndConfirm data={data} updateData={updateData}></OverviewAndConfirm>
    ])

    let currentIndexIsFotosForm = FOTOS_FORM_INDEX === step

    async function onSubmit(e) {
        e.preventDefault()
        if (!isLastStep) return next()
        uploadFiles()
        uploadRest()
        navigate("/")
    }

    async function uploadRest() {
        delete data.images
        console.log(data)
        const formData = new FormData()

        Object.keys(data).forEach(key => formData.append(key, data[key]))

        try {
            let res = await fetch("http://localhost:8080/accommodations/add", {
                method: 'POST',
                body: formData,
            })
            if (res.status === 200) {
                alert("You successfully published your accommodation!")
            } else {
                alert(`Error ${res.status}`)
            }
        } catch (err) {
            console.log(err)
        }
    }

    async function uploadFiles() {
        const formData = new FormData()
        for (let i = 0; i < data.images.length; i++) {
            formData.append(`image-${i}`, data.images[i], data.images.name)   
        }


        console.log(data.images.length)
        console.log(formData.get('image-0'))

        try {
            let res = await fetch("http://localhost:8080/accommodations/addImages", {
                method: 'POST',
                body: formData,
            })
            console.log(res.status)
            if (res.status !== 200) {
                alert(`Response status at file upload: ${res.status}`)
            }
        } catch (err) {
            console.log('Error while uploading images')
        }
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
                    <button disabled={currentIndexIsFotosForm && images.length > 0} type='submit'>{isLastStep ? 'Finish' : 'Next'}</button>
                </div>
            </form>
        </div>
    )
}

export default AccommodationForm