import React from 'react'
import FormWrapper from '../FormWrapper'
import Gallery from '../Gallery'

const PhotosForm = (props) => {

    return (
        <>
            <FormWrapper title="Photos">
                <label>
                    Add photos:
                    <input type='file' id='images' name='images' multiple
                        onChange={(e) => {
                            props.updateImages(e.target.files)
                            props.updateFiles(e.target.files)
                        }} required={props.images.length === 0} accept="image/*"></input>
                </label>
                <br></br>
            </FormWrapper>
            <Gallery images={props.images} files={props.files} />
        </>
    )
}

export default PhotosForm