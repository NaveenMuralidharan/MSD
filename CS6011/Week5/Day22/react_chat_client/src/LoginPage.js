

function LoginPage({handleJoin}){

    const handleClick = ()=>{
        const user = document.getElementById("userName").value;
        const room = document.getElementById("chatRoom").value;
        handleJoin(user, room);
    }

    return(
        <div id="joinForm">
            
            <label>User name: <input id="userName" type="text" /></label>
            <label>Chat room: <input id="chatRoom" type="text" /></label>
            <button id="joinButton" onClick={handleClick}>Join</button>
            
        </div>
    )
            
}

export default LoginPage;