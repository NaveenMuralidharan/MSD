
function MessageDisplay( {messages} ){
    console.log("here:",messages.length);
    return (
        
        <div>
            
            <p>test</p>
            <ul>
                <li>hi</li>
            {messages.map((message, index) => (
                <li key={index}>{ message }</li>
            ))}
            </ul>
            <p>here: {messages.length}</p>
        </div>
    );
}

export default MessageDisplay;