
let ws = new WebSocket("ws://localhost:8081");
ws.onopen = handleConnectCB;
ws.onclose = handleCloseCB;
ws.onerror = handleErrorCB;
ws.onmessage = handleMessageCB;

let joinForm = document.querySelector('#joinForm');
let messageForm = document.querySelector('#messageForm');
let userName = document.querySelector('#userName');
let chatRoom = document.querySelector('#chatRoom');
let joinButton = document.querySelector('#joinButton');
let messageDisplay = document.querySelector("#messageDisplay");
let messageInput = document.querySelector("#messageInput");
let sendButton = document.querySelector("#sendButton");
let leaveButton = document.querySelector("#leaveButton");
let isJoined = false;

joinButton.addEventListener("click", handleJoin);
sendButton.addEventListener("click", handleSend);
leaveButton.addEventListener("click", handleLeave);

messageForm.hidden = true;

function handleStyling(){
    if(isJoined){
        joinForm.hidden = true;
        messageForm.hidden = false;
    } else {
        joinForm.hidden = false;
        messageForm.hidden = true;
    }

}

function handleLeave(){
    console.log("leave sent");
    ws.send("leave");
    messageDisplay.innerHTML += "<p>"+userName.value+" has left chat room " + chatRoom.value +"</p>";
    isJoined = false;
    handleStyling();
}

function handleSend(){
    let msg = "message " + messageInput.value;
    ws.send(msg);
}

function handleJoin(){
    ws.send("join " + userName.value + " " + chatRoom.value);
}

function handleConnectCB(){
    console.log("connected");

}

function handleMessageCB(e){
      console.log(e);
//    let response = JSON.parse(e.data);
//    if(response.type == "join"){
//        let user = userName.value;
//        let chat = chatRoom.value;
//        userName.value = "";
//        chatRoom.value = "";
//        isJoined = true;
//        handleStyling();
//        messageDisplay.innerHTML += "<p>" + user +" Joined room " + chat + "</p>";
//    } else if(response.type == "message"){
//        messageInput.value = "";
//        messageDisplay.innerHTML += "<p>"+response.user+" : "+response.message+"</p>";
//    }
}

function handleErrorCB(){
    console.log("Error");
}

function handleCloseCB(){
    console.log("closed");
}

