import React from 'react'
import FormWrapper from '../FormWrapper'

const AccommodationDetailsForm = (props) => {
    return (
        <FormWrapper title="Accommodation Details">
            <label>
                Title:
            </label>
            <input type='text' id="title" name='title' required
                value={props.data.title || ''}
                onChange={(e) => props.updateData({ title: e.target.value })}
                autoFocus></input>
            <label>
                Capacity:
            </label>
            <input type='number' id='capacity' name='capacity'
                value={props.data.capacity || ''}
                onChange={(e) => props.updateData({ capacity: e.target.value })}
                required
                autoFocus></input>
            <label>
                Price per night:
            </label>
            <input type='number' id='pricePerNight' name='pricePerNight'
                value={props.data.pricePerNight || ''}
                onChange={(e) => props.updateData({ pricePerNight: e.target.value })}
                required></input>
            <label>
                Type:
            </label>
            <select id='type' name='type' value={props.data.type || ''}
                onChange={(e) => props.updateData({ type: e.target.value })}
                required>
                <option value='' disabled hidden>Choose here</option>
                <option value='ROOM'>Room</option>
                <option value='APARTMENT'>Apartment</option>
                <option value='HOUSE'>House</option>
            </select>
            <label>
                Description:
            </label>
            <textarea id='description' name='description'
                value={props.data.description || ''}
                onChange={(e) => props.updateData({ description: e.target.value })}
                required style={{ height: '150px' }}
            ></textarea>
        </FormWrapper>
    )
}

export default AccommodationDetailsForm