
function MessageInput({handleMessageSend, handleLeave}){

    const handleSubmit = ()=>{
        let messageInput = document.querySelector("#messageInput");

        handleMessageSend(messageInput.value);
        messageInput.value = "";
    }

    const handleLeaveSubmit = ()=>{
        handleLeave();
    }

    return (
        <div>
            <textarea id="messageInput"></textarea>
            <button id="sendButton" onClick={handleSubmit}>Send</button>
            <button id="leaveButton" onClick={handleLeaveSubmit}>Leave</button>
        </div>
    );

}

export default MessageInput;