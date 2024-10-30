
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
let roomsList = document.querySelector("#roomsList");
let roomsButton = document.querySelector("#getRooms");
let isJoined = false;

joinButton.addEventListener("click", handleJoin);
sendButton.addEventListener("click", handleSend);
leaveButton.addEventListener("click", handleLeave);
roomsButton.addEventListener("click", getRooms);

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
    let msg = "message," + messageInput.value;
    ws.send(msg);
}

function handleJoin(){
    ws.send("join," + userName.value + "," + chatRoom.value);
}

function handleConnectCB(){
    console.log("connected");

}

function handleMessageCB(e){
      console.log(e);
    let response = JSON.parse(e.data);
    console.log(response);
    console.log(response.type);

    if(response.type == "join"){
        let user = userName.value;
        let chat = chatRoom.value;
        userName.value = "";
        chatRoom.value = "";
        isJoined = true;
        messageDisplay.innerHTML = "";
        handleStyling();
//        messageDisplay.innerHTML += "<p>" + user +" Joined room " + chat + "</p>";
    }
    else if(response.type == "message"){
        messageInput.value = "";
        messageDisplay.innerHTML += "<p>"+response.user+" : "+response.message+"</p>";
    }
    else if(response.type == "rooms"){
        console.log(response.rooms);
        let rooms = response.rooms.split(",");
        console.log(rooms);
        rooms.forEach(room => {
            let newRoomLi = document.createElement("li");
            newRoomLi.innerText = room;
            roomsList.appendChild(newRoomLi);
        });
    }
}

function handleErrorCB(){
    console.log("Error");
}

function handleCloseCB(){
    console.log("closed");
}

function getRooms(){
    ws.send("rooms");
}