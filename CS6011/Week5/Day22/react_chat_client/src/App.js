import logo from './logo.svg';
import './App.css';
import LoginPage from './LoginPage';
import MessageDisplay from './MessageDisplay';
import { useEffect, useState } from 'react';

function App() {

  const[showMessages, setShowMessages] = useState(false);
  const [messages, setMessages] = useState([]);
  const [ws, setWs] = useState(null);

  useEffect(()=>{
  // Create WebSocket connection
  const webSocket = new WebSocket("ws://localhost:8081");
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
    console.log(user, room);
    //send join request
    ws.send(`join ${user} ${room}`);
    
  }


  function handleMessageCB(e){
    console.log(e);
    let response = JSON.parse(e.data);
    if(response.type == "join"){
      //hide the login form
      setShowMessages(true);
    } else if(response.type == "message"){
      const message = `${response.user} : ${response.message}`;
      console.log(message);
      // setMessages((prevMessages) => [...prevMessages, message]);
      let newMessages = messages;
      newMessages.push(message);
      setMessages(newMessages);
      console.log(messages);
    }
  }

  return (
    <div className="App">
      { !showMessages ? <LoginPage handleJoin = {handleJoin} /> : <></>}
      
      <MessageDisplay messages={messages} />


    </div>
  );
}

export default App;
