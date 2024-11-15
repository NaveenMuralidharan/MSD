const MessageForm = ({ messages, messageInput, setMessageInput, handleSend, handleLeave }) => {
    return (
      <div id="messageForm">
        <div id="messageDisplay">
          {messages.map((msg, index) => (
            <p key={index}>{`${msg.user}: ${msg.message}`}</p>
          ))}
        </div>
  
        <div>
          <textarea
            id="messageInput"
            value={messageInput}
            onChange={(e) => setMessageInput(e.target.value)}
          />
          <button id="sendButton" onClick={handleSend}>
            Send
          </button>
          <button id="leaveButton" onClick={handleLeave}>
            Leave
          </button>
        </div>
      </div>
    );
  };

  export default MessageForm;