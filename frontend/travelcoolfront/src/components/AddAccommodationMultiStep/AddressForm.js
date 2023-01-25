import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const AddressForm = (props) => {
    return (
        <AccommodationFormWrapper title="Address">
            <label>
                Country:
                <input type='text' id="country" name="country" 
                value={props.data.country || ''} 
                onChange={(e) => props.updateData({country: e.target.value})} 
                required
                autoFocus></input>
            </label>
            <label>
                City:
                <input type='text' id="city" name='city' 
                value={props.data.city || ''} 
                onChange={(e) => props.updateData({city: e.target.value})} 
                required></input>
            </label>
            <label>
                ZIP code:
                <input type='number' id="zipCode" name='zipCode' 
                value={props.data.zipCode || ''} 
                onChange={(e) => props.updateData({zipCode: e.target.value})} 
                required></input>
            </label>
            <label>
                Street:
                <input type='text' id='street' name='street' 
                value={props.data.street || ''} 
                onChange={(e) => props.updateData({street: e.target.value})} 
                required></input>
            </label>
            <label>
                House number:
                <input type='text' id='houseNumber' name='houseNumber' 
                value={props.data.houseNumber || ''} 
                onChange={(e) => props.updateData({houseNumber: e.target.value})} 
                required></input>
            </label>
        </AccommodationFormWrapper>
    )
}

export default AddressForm