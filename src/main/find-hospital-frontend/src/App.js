import {useState, useEffect} from 'react';
import axios from 'axios';

import logo from './logo.svg';
import './App.css';
import HospitalSearch from "./components/HospitalSearch";

function App() {
    //
    // const [hospitalList, setHospitalList] = useState(null);
    // const [page, setPage] = useState(2);
    // const [limit, setLimit] = useState(50);


  // fetch
  //   useEffect(()=>{
  //       axios.get(`http://localhost:8080/api/v1/hospitals?page=${page}&limit=${limit}`)
  //           .then((response) => setHospitalList(response.data));
  //           // .then((data) => setHospitalList(data))
  //   },[page,limit]);

  // react 에서는 상태가 바뀌면 re-rendering 한다 

  return (
    <div className="App">

        {/*<div>*/}
        {/*    <h1>Hospital List</h1>*/}
        {/*    {hospitalList ? (*/}
        {/*        <ul>*/}
        {/*            {hospitalList.content.map(hospital => (*/}
        {/*                <li key={hospital.id}>*/}
        {/*                    {hospital.hospitalName} - {hospital.address}*/}
        {/*                </li>*/}
        {/*            ))}*/}
        {/*        </ul>*/}
        {/*    ) : (*/}
        {/*        <p>Loading...</p>*/}
        {/*    )}*/}
        {/*</div>*/}

        <div>
            <h1>병원 검색</h1>
            <HospitalSearch />
        </div>

    </div>
    );
}

export default App;

// maximum call stack size error