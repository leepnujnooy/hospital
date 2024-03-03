package com.jp.findhospital.global.cache;

import com.jp.findhospital.domain.hospital.dto.HospitalResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheAccessAspect {
    private final CacheManager cacheManager;

//    @Pointcut("@annotation(org.springframework.cache.annotation.CachePut)&&args(id,dto)")
//    public void cacheableMethod(Long id,HospitalResponseDto dto){}
    @Pointcut("execution(* com.jp.findhospital.domain.hospital.service.HospitalService.updateCache(Long, com.jp.findhospital.domain.hospital.dto.HospitalResponseDto)) && args(id, dto)")
    public void updateCacheMethod(Long id, HospitalResponseDto dto) {}

    @AfterReturning(pointcut = "updateCacheMethod(id,dto)", returning = "result")
    public void countCacheAccess(Long id,HospitalResponseDto dto,Object result){
        Cache cache = cacheManager.getCache("hospitalIdCache");
//        log.info("포인트컷 캐치");
//        log.info(String.valueOf(id));

        HospitalResponseDto responseDto = cache.get(id, HospitalResponseDto.class);
        dto.setHits(dto.getHits()+1);
        log.info(String.valueOf(dto.getHits()));

        cache.put(id,dto);
    }

}
