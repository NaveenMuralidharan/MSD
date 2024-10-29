import React from 'react';

function ToDoList({todos, deleteTodo}){

    return(
        <div>
            { todos.map( (todo, index) => (
                <div 
                    key = {index}
                    onDoubleClick={ ()=> deleteTodo(index) }
                >
                    {todo}
                    <input type="checkbox"></input>
                </div>
            )) }
        </div>
    )


}

export default ToDoList;