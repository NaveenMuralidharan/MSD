
function MessageDisplay( {messages} ){
    return (
        <div>
            <p>test</p>
            <ul>
            {messages.map((message, index) => {
                console.log(message);
                <li key={index}>{ message }</li>
            })}
            </ul>
        </div>
    );
}

export default MessageDisplay;