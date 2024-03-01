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
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE a.hospitalName LIKE %:hospitalName%")
    Optional<List<Hospital>> findByHospitalName(@Param(value = "hospitalName") String hospitalName);

    //시도별 병원 조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE a.siDo = :siDo")
    Optional<List<Hospital>> findBySiDo(@Param(value = "siDo") String siDo);

    //병원타입별 병원 조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE a.hospitalType = :hospitalType")
    Optional<List<Hospital>> findByHospitalType(@Param(value = "hospitalType") String hospitalType);

    //병원타입, 병원이름 일치하는 병원 조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE " +
            "a.hospitalType = :hospitalType AND " +
            "a.hospitalName LIKE %:hospitalName%")
    Optional<List<Hospital>> findByNameAndType(
            @Param(value = "hospitalName") String hospitalName,
            @Param(value = "hospitalType") String hospitalType
    );

    //병원이름, 지역 일치하는 병원조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE " +
            "a.hospitalName LIKE %:hospitalName%  AND " +
            "a.siDo = :siDo")
    Optional<List<Hospital>> findByNameAndSido(
            @Param(value = "hospitalName") String hospitalName,
            @Param(value = "siDo") String siDo
    );

    //병원타입, 지역 일치하는 병원조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE " +
            "a.hospitalType = :hospitalType AND " +
            "a.siDo = :siDo")
    Optional<List<Hospital>> findByTypeAndSido(
            @Param(value = "hospitalType") String hospitalType,
            @Param(value = "siDo") String siDo
    );

    //병원타입, 지역 , 병원이름 일치하는 병원조회
    @Transactional
    @Modifying
    @Query(value = "SELECT a FROM Hospital a WHERE " +
            "a.hospitalType = :hospitalType AND " +
            "a.hospitalName LIKE %:hospitalName% AND " +
            "a.siDo = :siDo")
    Optional<List<Hospital>> findByNameAndTypeAndSido(
            @Param(value = "hospitalType") String hospitalType,
            @Param(value = "siDo") String siDo,
            @Param(value = "hospitalName") String hospitalName
    );



}
