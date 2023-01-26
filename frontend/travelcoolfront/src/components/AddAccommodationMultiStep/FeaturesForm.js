import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const FeaturesForm = (props) => {
    return (
        <AccommodationFormWrapper title="Features">
            <label>Parking:</label>
            <input type="checkbox" checked={props.data.parking || ''} 
            onChange={(e) => props.updateData({parking: e.target.checked})} 
            name="parking" 
            autoFocus/>
            <label>Pool:</label>
            <input type="checkbox" checked={props.data.pool || ''} 
            onChange={(e) => props.updateData({pool: e.target.checked})} 
            name="pool" />
            <label>Bed sheets:</label>
            <input type="checkbox" checked={props.data.bedSheets || ''} 
            onChange={(e) => props.updateData({bedSheets: e.target.checked})} 
            name="bedSheets" />
            <label>Towels:</label>
            <input type="checkbox" checked={props.data.towels || ''} 
            onChange={(e) => props.updateData({towels: e.target.checked})} 
            name="towels" />
            <label>Hair dryer:</label>
            <input type="checkbox" checked={props.data.hairDryer || ''} 
            onChange={(e) => props.updateData({hairDryer: e.target.checked})} 
            name="hairDryer" />
            <label>Kitchen:</label>
            <input type="checkbox" checked={props.data.kitchen || ''} 
            onChange={(e) => props.updateData({kitchen: e.target.checked})} 
            name="kitchen" />
            <label>Sauna:</label>
            <input type="checkbox" checked={props.data.sauna || ''} 
            onChange={(e) => props.updateData({sauna: e.target.checked})} 
            name="sauna" />
        </AccommodationFormWrapper>
    )
}

export default FeaturesForm