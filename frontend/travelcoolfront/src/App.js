import logo from './logo.svg';
import './App.css';

import { useState, useEffect } from "react";


function App() {
  return (
    <div className="App">
        <button id="clickButton" onClick={handleClick}>Click me!</button>
        <p>Message:</p>
    </div>
  );
}

let handleClick = async event =>{
    let response = await fetch('hello',{
        headers:{
            "Content-Type": "text",
        },
        method: "get",
    })
    let user = await response.text()
    createUser(user, event.target)
}

let createUser = function (user, button){
    let body = button.parentElement
    let userItem = document.createElement("h1")
    userItem.innerText = user
    body.prepend(button, userItem)
}
export default App;
