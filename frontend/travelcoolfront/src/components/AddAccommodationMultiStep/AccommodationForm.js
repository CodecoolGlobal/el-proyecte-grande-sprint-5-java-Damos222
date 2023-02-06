import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

import useMultipartForm from '../useMultistepForm'
import AccommodationDetailsForm from './AccommodationDetailsForm'
import AddressForm from './AddressForm'
import FeaturesForm from './FeaturesForm'
import PhotosForm from './PhotosForm'
import OverviewAndConfirm from './OverviewAndConfirm'
import '../../css/AccommodationForm.css'

const INITIAL_DATA = {
    title: '',
    country: '',
    street: '',
    zipCode: '',
    houseNumber: '',
    city: '',
    capacity: '',
    description: '',
    pricePerNight: '',
    type: '',
    parking: false,
    pool: false,
    towels: false,
    bedSheets: false,
    hairDryer: false,
    kitchen: false,
    sauna: false,
}

const AccommodationForm = () => {
    const navigate = useNavigate()
    const [images, setImages] = useState([])
    const [files, setFiles] = useState()
    const [data, setData] = useState(INITIAL_DATA)

    function updateImages(newFiles) {
        const newImages = []
        for (let file of newFiles) {
            newImages.push(URL.createObjectURL(file))
        }
        setImages((prev => {
            return [...prev, ...newImages]
        }))
    }

    function updateFiles(newFiles) {
        if (!files) {
            setFiles(newFiles)
            return
        }
        let imageDataTransfer = new DataTransfer()
        for (let file of newFiles) {
            imageDataTransfer.items.add(file)
        }
        for (let file of files) {
            imageDataTransfer.items.add(file)
        }
        setFiles(imageDataTransfer.files)
        console.log(files)
    }

    function updateData(newInput) {
        setData(prev => {
            return { ...prev, ...newInput }
        })
    }

    const { steps, currentStepIndex, step, isFirstStep, isLastStep, back, next } = useMultipartForm([
        <AddressForm data={data} updateData={updateData}></AddressForm>,
        <PhotosForm files={files} updateImages={updateImages} images={images} updateFiles={updateFiles}></PhotosForm>,
        <AccommodationDetailsForm data={data} updateData={updateData}></AccommodationDetailsForm>,
        <FeaturesForm data={data} updateData={updateData}></FeaturesForm>,
        <OverviewAndConfirm data={data} updateData={updateData} images={images} files={files}></OverviewAndConfirm>
    ])

    async function onSubmit(e) {
        e.preventDefault()
        if (!isLastStep) return next()
        uploadFiles()
        uploadRest()
        navigate("/")
    }

    async function uploadRest() {
        console.log(data)

        try {
            let res = await fetch("http://localhost:8080/accommodations/add", {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',  
                }
            })
            if (res.status === 200) {
                alert("You successfully published your accommodation!")
            } else {
                alert(`Error (${res.status})`)
            }
        } catch (err) {
            console.log(err)
        }
    }

    async function uploadFiles() {
        const formData = new FormData()
        for (let i = 0; i < files.length; i++) {
            formData.append(`image-${i}`, files[i], files[i].name)   
        }

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
        <div className='multiPartFormContainer'>
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