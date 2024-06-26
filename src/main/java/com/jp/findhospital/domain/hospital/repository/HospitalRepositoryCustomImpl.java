package com.jp.findhospital.domain.hospital.repository;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.entity.QHospital;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class HospitalRepositoryCustomImpl implements HospitalRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    QHospital hospital = QHospital.hospital;

    @Override
    public Optional<List<Hospital>> findByHospitalName(String hospitalName) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalName.likeIgnoreCase("%" + hospitalName + "%"))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findBySiDo(String siDo) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.siDo.eq(siDo))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findByHospitalType(String hospitalType) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalType.eq(hospitalType))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findByNameAndType(String hospitalName, String hospitalType) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalType.eq(hospitalType)
                        .and(hospital.hospitalName.likeIgnoreCase("%" + hospitalName + "%")))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findByNameAndSido(String hospitalName, String siDo) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalName.likeIgnoreCase("%" + hospitalName + "%")
                        .and(hospital.siDo.eq(siDo)))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findByTypeAndSido(String hospitalType, String siDo) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalType.eq(hospitalType)
                        .and(hospital.siDo.eq(siDo)))
                .fetch();
        return Optional.ofNullable(hospitals);
    }

    @Override
    public Optional<List<Hospital>> findByNameAndTypeAndSido(String hospitalType, String siDo, String hospitalName) {
        List<Hospital> hospitals = queryFactory
                .selectFrom(hospital)
                .where(hospital.hospitalType.eq(hospitalType)
                        .and(hospital.siDo.eq(siDo))
                        .and(hospital.hospitalName.likeIgnoreCase("%" + hospitalName + "%")))
                .fetch();
        return Optional.ofNullable(hospitals);
    }
}