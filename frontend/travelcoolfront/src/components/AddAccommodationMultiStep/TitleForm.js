import React from 'react'
import AccommodationFormWrapper from './AccommodationFormWrapper'

const TitleForm = (props) => {
    return (
        <AccommodationFormWrapper title="Title">
            <label>
                Title:
                <input type='text' id="title" name='title' required value={props.data.title || ''} onChange={(e) => props.updateData({title: e.target.value})}></input>
            </label>
        </AccommodationFormWrapper>
    )
}

export default TitleForm