import React from 'react'
import FormWrapper from '../FormWrapper'

const AddressForm = (props) => {
    return (
        <FormWrapper title="Address">
            <label>
                Country:
            </label>
            <input type='text' id="country" name="country"
                value={props.data.country || ''}
                onChange={(e) => props.updateData({ country: e.target.value })}
                required
                autoFocus></input>
            <label>
                City:
            </label>
            <input type='text' id="city" name='city'
                value={props.data.city || ''}
                onChange={(e) => props.updateData({ city: e.target.value })}
                required></input>
            <label>
                ZIP code:
            </label>
            <input type='number' id="zipCode" name='zipCode'
                value={props.data.zipCode || ''}
                onChange={(e) => props.updateData({ zipCode: e.target.value })}
                required></input>
            <label>
                Street:
            </label>
            <input type='text' id='street' name='street'
                value={props.data.street || ''}
                onChange={(e) => props.updateData({ street: e.target.value })}
                required></input>
            <label>
                House number:
            </label>
            <input type='text' id='houseNumber' name='houseNumber'
                value={props.data.houseNumber || ''}
                onChange={(e) => props.updateData({ houseNumber: e.target.value })}
                required></input>
        </FormWrapper>
    )
}

export default AddressForm