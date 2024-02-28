import {useState, useEffect} from 'react';
import axios from 'axios';

import logo from './logo.svg';
import './App.css';

function App() {

    const [hospitalList, setHospitalList] = useState(null);
    const [page, setPage] = useState(1);
    const [limit, setLimit] = useState(10);


  // fetch
    useEffect(()=>{
        fetch('http://localhost:8080/api/v1/hospitals?page=1&limit=10')
            .then((response) => response.json())
            .then((data) => setHospitalList(data))
    })

  // react 에서는 상태가 바뀌면 re-rendering 한다 

  return (
    <div className="App">

        <div>
            <h1>Hospital List</h1>
            {hospitalList ? (
                <ul>
                    {hospitalList.content.map(hospital => (
                        <li key={hospital.id}>
                            {hospital.hospitalName} - {hospital.address}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    </div>
    );
}

export default App;

// maximum call stack size error