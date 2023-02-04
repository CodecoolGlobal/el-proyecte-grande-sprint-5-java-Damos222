import React from 'react'
<<<<<<< HEAD
import FormWrapper from '../FormWrapper'
import Gallery from '../Gallery'
=======
import AccommodationFormWrapper from './AccommodationFormWrapper'
import "../../css/FotosForm.css"
>>>>>>> development

const PhotosForm = (props) => {

    return (
        <>
<<<<<<< HEAD
            <FormWrapper title="Fotos">
                <label>
                    Add photos:
=======
            <AccommodationFormWrapper title="Fotos">
                <label>
                    Add fotos:
>>>>>>> development
                    <input type='file' id='images' name='images' multiple
                        onChange={(e) => {
                            props.updateImages(e.target.files)
                            props.updateFiles(e.target.files)
                        }} required={props.images.length === 0} accept="image/*"></input>
                </label>
                <br></br>
<<<<<<< HEAD
            </FormWrapper>
            <Gallery images={props.images} files={props.files} />
=======
            </AccommodationFormWrapper>
            <div id='gallery'>
                {props.images && props.images.map((image, i) => {
                    return (
                        <figure key={i}>
                            <img  className='singleImage' src={image} alt='' style={{ width: "100px" }}></img>
                            <figcaption >{props.files[i].name}</figcaption>
                        </figure>
                    )
                })}
            </div>
>>>>>>> development
        </>
    )
}

export default PhotosForm