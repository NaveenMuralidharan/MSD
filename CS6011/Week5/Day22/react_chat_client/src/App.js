// src/App.js
import React, { useState, useEffect } from 'react';
import './App.css';
import JoinForm from './JoinForm';
import MessageForm from './MessageForm';

// WebSocket URL (change if necessary)
const wsUrl = "ws://localhost:8080";

const App = () => {
  // State variables
  const [ws, setWs] = useState(null);
  const [isJoined, setIsJoined] = useState(false);
  const [userName, setUserName] = useState('');
  const [chatRoom, setChatRoom] = useState('');
  const [messages, setMessages] = useState([]);
  const [messageInput, setMessageInput] = useState('');

  // Open WebSocket connection on mount
  useEffect(() => {
    const socket = new WebSocket(wsUrl);
    
    socket.onopen = handleConnect;
    socket.onclose = handleClose;
    socket.onerror = handleError;
    socket.onmessage = handleMessage;

    setWs(socket);

    return () => {
      socket.close(); // Close connection on cleanup
    };
  }, []);

  const handleConnect = () => {
    console.log("Connected to WebSocket");
  };

  const handleClose = () => {
    console.log("Connection closed");
  };

  const handleError = () => {
    console.log("Error with WebSocket");
  };

  const handleMessage = (event) => {
    const response = JSON.parse(event.data);
    if (response.type === 'join') {
      setMessages((prevMessages) => [
        ...prevMessages,
        { user: userName, message: `Joined room ${chatRoom}` }
      ]);
      setIsJoined(true);
    } else if (response.type === 'message') {
      setMessages((prevMessages) => [
        ...prevMessages,
        { user: response.user, message: response.message }
      ]);
    }
  };

  const handleJoin = () => {
    if (ws && userName && chatRoom) {
      ws.send(`join ${userName} ${chatRoom}`);
      setUserName('');
      setChatRoom('');
    }
  };

  const handleSend = () => {
    if (ws && messageInput) {
      const msg = `message ${messageInput}`;
      ws.send(msg);
      setMessageInput('');
    }
  };

  const handleLeave = () => {
    if (ws) {
      ws.send('leave');
      setMessages((prevMessages) => [
        ...prevMessages,
        { user: userName, message: `has left chat room ${chatRoom}` }
      ]);
      setIsJoined(false);
    }
  };

  const handleStyling = () => {
    return isJoined ? 'joined' : 'not-joined';
  };

  return (
    <div className="App">
      <div className={handleStyling()}>
        {!isJoined ? (
          <JoinForm
            userName={userName}
            setUserName={setUserName}
            chatRoom={chatRoom}
            setChatRoom={setChatRoom}
            handleJoin={handleJoin}
          />
        ) : (
          <MessageForm
            messages={messages}
            messageInput={messageInput}
            setMessageInput={setMessageInput}
            handleSend={handleSend}
            handleLeave={handleLeave}
          />
        )}
      </div>
    </div>
  );
};


export default App;
