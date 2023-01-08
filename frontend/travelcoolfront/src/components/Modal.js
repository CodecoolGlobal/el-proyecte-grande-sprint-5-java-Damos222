import ReactDom from 'react-dom'

const Modal = (props) => {
    if (!props.open) {
        return null
    }
    return ReactDom.createPortal(
        <div className="overlay" onClick={props.onClose}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
                <button onClick={props.onClose}>close</button>
                {props.children}
            </div>
        </div>,
        document.getElementById("portal")
    )
}

export default Modal