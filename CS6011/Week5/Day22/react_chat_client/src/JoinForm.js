const JoinForm = ({ userName, setUserName, chatRoom, setChatRoom, handleJoin }) => {
    return (
      <div id="joinForm">
        <label>
          User name:
          <input
            type="text"
            id="userName"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
          />
        </label>
        <label>
          Chat room:
          <input
            type="text"
            id="chatRoom"
            value={chatRoom}
            onChange={(e) => setChatRoom(e.target.value)}
          />
        </label>
        <button id="joinButton" onClick={handleJoin}>
          Join
        </button>
      </div>
    );
  };

  export default JoinForm;