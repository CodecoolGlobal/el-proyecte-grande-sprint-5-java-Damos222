import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const FotosForm = (props) => {
    return (
        <AccommodationFormWrapper title="Fotos">
            <label>
                Add fotos:
                <input type='file' id='image' name='image' multiple value={props.data.images || ''} onChange={(e) => props.updateData({images: e.target.value})} required></input>
            </label>
        </AccommodationFormWrapper>
    )
}

export default FotosForm