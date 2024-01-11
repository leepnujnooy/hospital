package com.jp.findhospital.domain.hospital.repository;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    //병원이름으로 단일 병원 조회
    //옵셔널 안에 병원정보를 감싼다.
    Optional<Hospital> findByHospitalName(String hospitalName);

    //시도별 병원 조회
    //리스트안에 병원들을 넣어 감싼다.
    Optional<List<Hospital>> findAllBySiDo(String siDo);

    //시군구별 병원 조회
    Optional<List<Hospital>> findAllBySiGunGu(String siGunGu);

    //병원타입별 병원 조회
    Optional<List<Hospital>> findAllByHospitalType(String hospitalType);


}
