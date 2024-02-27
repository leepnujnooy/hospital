import React, {useState, useEffect} from 'react';
import axios from 'axios';

import logo from './logo.svg';
import './App.css';

function App() {

  

  // fetch 
  fetch('http://localhost:8080/api/v1/hospitals')
  .then((response) => response.json())
  .then((data) => console.log(data))

  // react 에서는 상태가 바뀌면 re-rendering 한다 

  return (
    <div className="App">

      <h1>hospital api v1</h1>
      
    </div>
  );
}

export default App;
