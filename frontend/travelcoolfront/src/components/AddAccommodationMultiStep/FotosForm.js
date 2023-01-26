import React from 'react'
import FormWrapper from '../FormWrapper'
import "../../css/FotosForm.css"

const FotosForm = (props) => {

    return (
        <>
            <FormWrapper title="Fotos">
                <label>
                    Add fotos:
                    <input type='file' id='images' name='images' multiple
                        onChange={(e) => {
                            props.updateImages(e.target.files)
                            props.updateFiles(e.target.files)
                        }} required={props.images.length === 0} accept="image/*"></input>
                </label>
                <br></br>
            </FormWrapper>
            <div id='gallery'>
                {props.images && props.images.map((image, i) => {
                    return (
                        <figure key={i + 1}>
                            <img key={(i + 1) * 3} className='singleImage' src={image} alt='' style={{ width: "100px" }}></img>
                            <figcaption key={(i + 1) * 2}>{props.files[i].name}</figcaption>
                        </figure>
                    )
                })}
            </div>
        </>
    )
}

export default FotosForm