import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const FotosForm = (props) => {

    return (
        <AccommodationFormWrapper title="Fotos">
            <label>
                Add fotos:
                <input type='file' id='images' name='images' multiple
                    onChange={(e) => {
                        props.updateImages(e.target.files)
                        props.updateData({images: e.target.files})
                    }} required={props.images.length === 0} accept="image/*"></input>
            </label>
            <div id='gallery'>
                {props.images && props.images.map((image) => {
                    return (
                        <img key={image} className='singleImage' src={image} alt='' style={{width: "300px"}}></img>
                    )
                })}
            </div>
        </AccommodationFormWrapper>
    )
}

export default FotosForm