package com.jp.findhospital.global.cache;

import com.jp.findhospital.domain.hospital.dto.HospitalResponseDto;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheScheduler {

    private final CacheManager cacheManager;
    private final HospitalService hospitalService;
    private final HospitalRepository hospitalRepository;


    //IdCache 에 저장된 조회수 DB 업데이트
    @Scheduled(fixedDelay = 180000) //3분마다 처리
    public void updateHits(){
        Cache cache = cacheManager.getCache("hospitalIdCache");

        int entireNum = hospitalRepository.findAll().size();


        for (Long i = 0L; i < entireNum; i++) {
            HospitalResponseDto access = cache.get(i,HospitalResponseDto.class);
            if(access != null){
                int totalAccess = access.getHits();
                HospitalResponseDto dto = hospitalService.getHospitalById(i);
                dto.setHits(access.getHits() + totalAccess);
                hospitalRepository.save(Hospital
                        .builder()
                        .id(dto.getId())
                        .hospitalName(dto.getHospitalName())
                        .hospitalType(dto.getHospitalType())
                        .mailNumber(dto.getMailNumber())
                        .hospitalStartDate(dto.getHospitalStartDate())
                        .address(dto.getAddress())
                        .hospitalPhoneNumber(dto.getHospitalPhoneNumber())
                        .siDo(dto.getSiDo())
                        .gunGu(dto.getGunGu())
                        .x(dto.getX())
                        .y(dto.getY())
                        .score(dto.getScore())
                        .hits(totalAccess)
                        .build());
                cache.evict(i);
            }
        }
    }


}
