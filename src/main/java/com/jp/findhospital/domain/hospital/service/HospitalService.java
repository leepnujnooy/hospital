package com.jp.findhospital.domain.hospital.service;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) //모든 메서드 단일 트랜잭션 내에서 수행, 읽기 전용 (수정 ㄴㄴ)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원 전체조회
    public List<Hospital> getHospitalAll(){
        return hospitalRepository.findAll();
    }

    //id로 단일 병원 조회
    public Hospital getHospitalById(Long id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);

        if(optionalHospital.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        return optionalHospital.get();
    }

    //이름으로 단일 병원 조회
    public List<Hospital> getHospital(String hospitalName){
        Optional<List<Hospital>> optionalHospital = hospitalRepository.findByHospitalName(hospitalName);

        if(optionalHospital.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        return optionalHospital.get();
    }

    //시도별 병원 조회
    public List<Hospital> getHospitalBySido(String siDo){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findAllBySiDoContaining(siDo);

        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        return optionalHospitals.get();
    }

    //시도별 병원 조회
    public List<Hospital> getHospitalBySiGunGu(String siGunGu){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findAllBySiGunGuContaining(siGunGu);

        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        return optionalHospitals.get();
    }

    //병원타입별 병원 조회
    public List<Hospital> getHospitalByType(String type){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findAllByHospitalType(type);

        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        return optionalHospitals.get();
    }

}
