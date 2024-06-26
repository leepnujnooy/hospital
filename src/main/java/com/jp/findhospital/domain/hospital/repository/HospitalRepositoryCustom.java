package com.jp.findhospital.domain.hospital.repository;

import com.jp.findhospital.domain.hospital.entity.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalRepositoryCustom {
    Optional<List<Hospital>> findByHospitalName(String hospitalName);
    Optional<List<Hospital>> findBySiDo(String siDo);
    Optional<List<Hospital>> findByHospitalType(String hospitalType);
    Optional<List<Hospital>> findByNameAndType(String hospitalName, String hospitalType);
    Optional<List<Hospital>> findByNameAndSido(String hospitalName, String siDo);
    Optional<List<Hospital>> findByTypeAndSido(String hospitalType, String siDo);
    Optional<List<Hospital>> findByNameAndTypeAndSido(String hospitalType, String siDo, String hospitalName);
}