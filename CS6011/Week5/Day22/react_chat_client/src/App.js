import logo from './logo.svg';
import './App.css';
import LoginPage from './LoginPage';
import MessageDisplay from './MessageDisplay';
import MessageInput from './MessageInput';
import { useEffect, useState } from 'react';

function App() {

  const[showMessages, setShowMessages] = useState(false);
  const [messages, setMessages] = useState([]);
  const [ws, setWs] = useState(null);

  useEffect(()=>{
    // Create WebSocket connection
    const webSocket = new WebSocket("ws://localhost:8081");
    //jar file test
    // const webSocket = new WebSocket("ws://localhost:8080");
    setWs(webSocket);

    // Connection opened
    webSocket.onopen = () => {
      console.log("connected");
    };

    // Listen for messages
    webSocket.onmessage = handleMessageCB;

    return ()=>{ webSocket.close();}
  }, []);

  const handleJoin = (user, room)=>{

    //send join request
    ws.send(`join,${user},${room}`);
    //jar file test
    // ws.send(`join ${user} ${room}`);
    
  }

  const handleMessageSend = (message) => {
    ws.send(`message,${message}`);
    //jar file test
    // ws.send(`message ${message}`);
  }

  const handleLeave = () => {
    ws.send("leave");
    //clear messages from old room
    let clearedMessages = [];
    setMessages([]);
    
    setShowMessages(false);
  }

  function handleMessageCB(e){
    console.log(e);
    let response = JSON.parse(e.data);
    if(response.type == "join"){
      //hide the login form
      console.log("show messages",showMessages);
      setShowMessages(true);
    } else if(response.type == "message"){
      const message = `${response.user} : ${response.message}`;
      console.log(message);
      // setMessages((prevMessages) => [...prevMessages, message]);
      let newMessages = messages;
      newMessages.push(message);
      setMessages(newMessages);
      // setShowMessages(true);
      console.log(messages);
    }
  }

  return (
    <div className="App">
      { !showMessages ? <LoginPage handleJoin = {handleJoin} /> : 
        <div>
          <MessageDisplay messages={messages} />
          <MessageInput handleMessageSend={handleMessageSend} handleLeave={handleLeave} />
        </div>
      }

    </div>
  );
}

export default App;
