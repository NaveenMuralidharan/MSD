import React, {useState} from "react";

function InputArea({addTodo}){

    const[inputValue, setInputValue] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if(inputValue.trim()){
            addTodo(inputValue);
            setInputValue('');
        }
        
    }

    return(
        <div>
            <form onSubmit={handleSubmit}>
                <input 
                    type="text>"
                    placeholder="Add new task..."
                    value = {inputValue}
                    onChange={(e)=> setInputValue(e.target.value)}
                />
                <button type="submit">Add</button>
            </form>
        </div>
    );

}

export default InputArea;