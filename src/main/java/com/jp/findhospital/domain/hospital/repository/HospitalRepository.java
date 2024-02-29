package com.jp.findhospital.domain.hospital.repository;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    //병원이름으로 단일 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.hospitalName LIKE %:hospitalName%")
    Optional<List<Hospital>> findByHospitalName(String hospitalName);

    //시도별 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.siDo = :sido")
    Optional<List<Hospital>> findAllBySiDoContaining(String sido);

    //병원타입별 병원 조회
    @Query("SELECT a FROM Hospital a WHERE a.hospitalType = :hospitalType")
    Optional<List<Hospital>> findAllByHospitalType(String hospitalType);

    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE " +
            ":hospitalType is null or a.hospitalType = :hospitalType AND " +
            ":hospitalName is null or a.hospitalName LIKE :hospitalName%  AND " +
            ":siDo is null or a.siDo = :siDo")
    Optional<List<Hospital>> findCriteria(
            @Param(value = "hospitalName") String hospitalName,
            @Param(value = "hospitalType") String hospitalType,
            @Param(value = "siDo") String siDo
    );

}
