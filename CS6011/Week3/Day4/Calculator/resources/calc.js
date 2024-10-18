"use strict";

console.log("JS connected");

//callback functions:

function handleConnectCB(){
    console.log("WS connected");
}

function handleMessageCB(event){
    console.log("WS sent a msg");
    console.log(event);
    wsTA.value = event.data;
}

function handleCloseCB(){
    console.log("WS closed");
    wsTA.value = "server closed";
}

function handleWSErrorCB(){
    console.log("WS error");
}

function handleLoadCB(){
    console.log("got load");
    resultTA.value = this.responseText;
}

function handleErrorCB(){
    console.log("An AJAX error occured");
}

function handleKeyPressCB(event){

    if(event.type == "click" || event.keyCode == 13){
        let x = Number(xTA.value);
        let y = Number(yTA.value);
        event.preventDefault();

        if(isNaN(x)){
           alert("Please make sure x is a number");
           xTA.value ="<Enter Number>";
           xTA.select();
           return;
        }
        if(isNaN(y)){
            alert("Please make sure y is a number");
            yTA.value ="<Enter Number>";
            yTA.select();
            return;
        }
        //use AJAx to request calculated result from server
        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/calculate?x="+x+"&y="+y);
        request.addEventListener("load", handleLoadCB);
        request.addEventListener("error", handleErrorCB);
        request.send();

        //use WS for request
        ws.send(x + " " + y);

    }
}

let xTA = document.querySelector('#xValue');
let yTA = document.querySelector('#yValue');
let resultTA = document.querySelector('#result');
let button = document.querySelector('button');
button.addEventListener("click", handleKeyPressCB);
let wsTA = document.querySelector('#wsTA');


xTA.addEventListener("keypress", handleKeyPressCB);
yTA.addEventListener("keypress", handleKeyPressCB);

let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onclose = handleCloseCB;
ws.onmessage = handleMessageCB;
ws.onerror = handleWSErrorCB;

