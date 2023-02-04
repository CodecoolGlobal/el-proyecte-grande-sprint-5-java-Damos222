import React from 'react'
import '../css/Gallery.css'

const Gallery = (props) => {
    return (
        <div id='gallery'>
            {props.images && props.images.map((image, i) => {
                return (
                    <figure key={i}>
                        <img className='singleImage' src={image} alt='' style={{ width: "100px" }}></img>
                        <figcaption>{props.files[i].name}</figcaption>
                    </figure>
                )
            })}
        </div>
    )
}

export default Gallery