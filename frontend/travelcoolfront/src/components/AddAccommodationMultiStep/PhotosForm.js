import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'
import "../../css/FotosForm.css"

const PhotosForm = (props) => {

    return (
        <>
            <AccommodationFormWrapper title="Fotos">
                <label>
                    Add fotos:
                    <input type='file' id='images' name='images' multiple
                        onChange={(e) => {
                            props.updateImages(e.target.files)
                            props.updateFiles(e.target.files)
                        }} required={props.images.length === 0} accept="image/*"></input>
                </label>
                <br></br>
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
        </>
    )
}

export default PhotosForm