// App.js
import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, FlatList, StyleSheet } from 'react-native';

const wsUrl = "ws://localhost:8080"; // WebSocket URL (change if necessary)

const App = () => {
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
        { user: userName, message: `Joined room ${chatRoom}` },
      ]);
      setIsJoined(true);
    } else if (response.type === 'message') {
      setMessages((prevMessages) => [
        ...prevMessages,
        { user: response.user, message: response.message },
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
        { user: userName, message: `has left chat room ${chatRoom}` },
      ]);
      setIsJoined(false);
    }
  };

  return (
    <View style={styles.container}>
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
    </View>
  );
};

const JoinForm = ({ userName, setUserName, chatRoom, setChatRoom, handleJoin }) => {
  return (
    <View style={styles.formContainer}>
      <Text style={styles.label}>User Name:</Text>
      <TextInput
        style={styles.input}
        value={userName}
        onChangeText={setUserName}
      />
      <Text style={styles.label}>Chat Room:</Text>
      <TextInput
        style={styles.input}
        value={chatRoom}
        onChangeText={setChatRoom}
      />
      <Button title="Join" onPress={handleJoin} />
    </View>
  );
};

const MessageForm = ({ messages, messageInput, setMessageInput, handleSend, handleLeave }) => {
  return (
    <View style={styles.formContainer}>
      <FlatList
        data={messages}
        renderItem={({ item }) => (
          <Text style={styles.message}>
            {item.user}: {item.message}
          </Text>
        )}
        keyExtractor={(item, index) => index.toString()}
        style={styles.messageList}
      />
      <TextInput
        style={styles.input}
        value={messageInput}
        onChangeText={setMessageInput}
        placeholder="Type a message..."
      />
      <View style={styles.buttonContainer}>
        <Button title="Send" onPress={handleSend} />
        <Button title="Leave" onPress={handleLeave} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  formContainer: {
    width: '100%',
    padding: 16,
    alignItems: 'center',
  },
  label: {
    fontSize: 16,
    marginBottom: 8,
  },
  input: {
    width: '100%',
    padding: 10,
    marginBottom: 16,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 5,
  },
  messageList: {
    width: '100%',
    height: 300,
    marginBottom: 16,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 5,
    padding: 10,
    backgroundColor: '#f9f9f9',
  },
  message: {
    fontSize: 14,
    marginBottom: 8,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
  },
});

export default App;
