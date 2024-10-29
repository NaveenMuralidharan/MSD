import logo from './logo.svg';
import './App.css';
import {useRef, useState} from 'react';
import ToDoList from './ToDoList';
import InputArea from './InputArea';

function App() {
  
 const[todos, setTodos] = useState([]);

 const addTodo = (todo) => {
    setTodos((prevTodos) => [...prevTodos, todo]);
 } 

 const deleteTodo = (index) => {
    let newTodos = [...todos];
    newTodos.splice(index, 1);
    setTodos(newTodos);
 }

  return (
    <div className="App">
      <h1>To-Do List</h1>
      <ToDoList todos={todos} deleteTodo={deleteTodo}/>
      <InputArea addTodo={addTodo} />
      
    </div>
  );
}

export default App;
