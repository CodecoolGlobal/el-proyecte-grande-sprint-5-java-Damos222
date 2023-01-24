import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const AccommodationDetailsForm = (props) => {
    return (
        <AccommodationFormWrapper title="Accommodation Details">
            <label>
                Capacity:
                <input type='number' id='capacity' name='capacity' value={props.data.capacity || ''} onChange={(e) => props.updateData({capacity: e.target.value})} required></input>
            </label>
            <label>
                Description (at least 400 characters):
                <textarea id='description' name='description' value={props.data.description || ''} onChange={(e) => props.updateData({description: e.target.value})} required></textarea>
            </label>
            <label>
                Price per night:
                <input type='number' id='pricePerNight' name='pricePerNight' value={props.data.pricePerNight || ''} onChange={(e) => props.updateData({pricePerNight: e.target.value})} required></input>
            </label>
            <label>
                Type:
                <select id='type' name='type' value={props.data.type || ''} onChange={(e) => props.updateData({type: e.target.value})} required>
                    <option value='room'>Room</option>
                    <option value='appartment'>Appartment</option>
                    <option value='house'>House</option>
                </select>
            </label>
        </AccommodationFormWrapper>
    )
}

export default AccommodationDetailsForm