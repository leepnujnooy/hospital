import React, { useState } from 'react';
import axios from 'axios';

const HospitalSearch = () => {
    const [hospitalName, setHospitalName] = useState('');
    const [hospitalType, setHospitalType] = useState('');
    const [siDo, setSiDo] = useState('');
    const [result, setResult] = useState([]);

    const handleSearch = () => {
        axios.get(`http://localhost:8080/api/v1/hospitals/condition`, {
            params: {
                hospitalName: hospitalName,
                hospitalType: hospitalType,
                siDo: siDo
            }
        })
            .then(response => {
                // 여기서 응답 처리
                console.log(response.data);
                setResult(response.data.content);
            })
            .catch(error => {
                // 에러 처리
                console.error('Error fetching data: ', error);
            });
    };

    return (
        <div>
            <input
                type="text"
                placeholder="병원 이름을 입력하세요"
                value={hospitalName}
                onChange={(e) => setHospitalName(e.target.value)}
            />
            <div>
                <button onClick={handleSearch}>검색</button>
            </div>
            <div>
                <label>병원 유형:</label>
                <select value={hospitalType} onChange={(e) => setHospitalType(e.target.value)}>
                    <option value="">전체</option>
                    <option value="상급종합">상급종합병원</option>
                    <option value="종합병원">종합병원</option>
                    <option value="병원">병원</option>
                    <option value="요양병원">요양병원</option>
                </select>
            </div>
            <div>
                <label>지역:</label>
                <select value={siDo} onChange={(e) => setSiDo(e.target.value)}>
                    <option value="">전체</option>
                    <option value="서울">서울</option>
                    <option value="부산">부산</option>
                    <option value="울산">울산</option>
                    <option value="대구">대구</option>
                </select>
            </div>
            <div>
                <h2>Search Result:</h2>
                <ul>
                    {result.map((hospital) => (
                        <li key={hospital.id}>
                            {hospital.hospitalName} - {hospital.hospitalType} - {hospital.siDo}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default HospitalSearch;
