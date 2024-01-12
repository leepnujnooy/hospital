package com.jp.findhospital.domain.hospital.repository;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    //병원이름으로 단일 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.hospitalName LIKE %:hospitalName%")
    Optional<List<Hospital>> findByHospitalName(String hospitalName);

    //시도별 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.siDo = :sido")
    Optional<List<Hospital>> findAllBySiDoContaining(String sido);

    //시군구별 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.siGunGu LIKE :siGunGu%")
    Optional<List<Hospital>> findAllBySiGunGuContaining(String siGunGu);

    //병원타입별 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.hospitalType = :hospitalType")
    Optional<List<Hospital>> findAllByHospitalType(String hospitalType);


}
