import React from 'react'
import "../css/Button.css"

const Button = ({type, size, content, onClick}) => {
  return (
    <>
        <button className={`button ${type} ${size}`} onClick={onClick}>{content}</button>
    </>
  )
}

export default Button