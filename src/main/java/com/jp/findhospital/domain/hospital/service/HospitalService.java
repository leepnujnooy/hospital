package com.jp.findhospital.domain.hospital.service;

import com.jp.findhospital.domain.hospital.dto.HospitalResponseDto;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional //모든 메서드 단일 트랜잭션 내에서 수행, 읽기 전용 (수정 ㄴㄴ)
public class HospitalService {

    private final CacheManager cacheManager;
    private final HospitalRepository hospitalRepository;


    //id로 단일 병원 조회
    //조회 할 때마다 조회수를 증가시킴 => 캐시에 저장후 3분마다 업데이트. 캐시초기화

    @Cacheable(value = "hospitalIdCache", key = "#id")
    public HospitalResponseDto getHospitalById(Long id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");
        return HospitalResponseDto.entityToDto(optionalHospital.get());
    }

    public HospitalResponseDto getHospitalByIdNoCache(Long id){
        Hospital hospital = hospitalRepository.findById(id).orElseThrow();
        hospitalRepository.save(Hospital.builder()
                .id(hospital.getId())
                .x(hospital.getY())
                .y(hospital.getY())
                .commentList(hospital.getCommentList())
                .gunGu(hospital.getGunGu())
                .hits(hospital.getHits()+1)
                .siDo(hospital.getSiDo())
                .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                .hospitalName(hospital.getHospitalName())
                .score(hospital.getScore())
                .address(hospital.getAddress())
                .hospitalType(hospital.getHospitalType())
                .hospitalStartDate(hospital.getHospitalStartDate())
                .mailNumber(hospital.getMailNumber())
                .build());
        return HospitalResponseDto.entityToDto(hospital);
    }

    @CachePut(value = "hospitalIdCache", key = "#id")
    public HospitalResponseDto updateCache(Long id, HospitalResponseDto dto){
        return dto;
    }

    //병원 전체조회
    public List<HospitalResponseDto> getHospitalAll(){
        //페이지 request 를 정의
        //        Pageable pageable = PageRequest.of(currentPage, perPage, Sort.by("id"));
        //        Page<Hospital> hospitalPage = hospitalRepository.findAll(pageable);

        return hospitalRepository
                .findAll()
                .stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //병원이름으로 조회
    public List<HospitalResponseDto> getHospitalByName(String hospitalName){

        List<Hospital> hospitalList = hospitalRepository.findByHospitalName(hospitalName).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //시도 병원 조회
    public List<HospitalResponseDto> getHospitalBySido(String siDo){
        List<Hospital> hospitalList = hospitalRepository.findBySiDo(siDo).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }


    //병원타입 필터만 거친 병원 조회
    public List<HospitalResponseDto> getHospitalByType(String hospitalType){
        List<Hospital> hospitalList = hospitalRepository.findByHospitalType(hospitalType).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //병원이름, 병원타입
    public List<HospitalResponseDto> getHospitalsByNameAndType(
            String hospitalName,
            String hospitalType){
        List<Hospital> hospitalList = hospitalRepository.findByNameAndType(hospitalName,hospitalType).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //병원이름, 지역
    public List<HospitalResponseDto> getHospitalsByNameAndSido(
            String hospitalName,
            String siDo){

        List<Hospital> hospitalList = hospitalRepository.findByNameAndSido(hospitalName,siDo).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //병원타입, 지역
    public List<HospitalResponseDto> getHospitalsByTypeAndSido(
            String hospitalType,
            String siDo){
        List<Hospital> hospitalList = hospitalRepository.findByTypeAndSido(hospitalType,siDo).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }

    //병원타입, 지역
    public List<HospitalResponseDto> getHospitalsByNameAndTypeAndSido(
            String hospitalName,
            String hospitalType,
            String siDo){
        List<Hospital> hospitalList = hospitalRepository.findByNameAndTypeAndSido(hospitalName,hospitalType,siDo).orElseThrow();

        return  hospitalList.stream()
                .map(hospital->HospitalResponseDto
                        .builder()
                        .id(hospital.getId())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalType(hospital.getHospitalType())
                        .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                        .siDo(hospital.getSiDo())
                        .gunGu(hospital.getGunGu())
                        .mailNumber(hospital.getMailNumber())
                        .x(hospital.getX())
                        .y(hospital.getY())
                        .address(hospital.getAddress())
                        .score(hospital.getScore())
                        .hospitalStartDate(hospital.getHospitalStartDate())
                        .hits(hospital.getHits())
                        .build())
                .collect(Collectors.toList());
    }


    //조회 후 조건에 맞는 병원리스트 간추리는 메서드
//    @Cacheable(
//            value = "hospitalPageCache",
//            key = "#hospitalName + '-' + '-' + #hospitalType + '-' + #siDo")
    public List<HospitalResponseDto> getHospitals(
            String hospitalType,
            String hospitalName,
            String siDo
    ){
        //경우의 수 =>
        //1. 아무런 조건 없이 검색
        if(hospitalName == null && hospitalType == null && siDo == null){
            return getHospitalAll();
        }
        //2. 병원타입 조건만 검색
        else if(hospitalName == null && hospitalType != null && siDo == null){
            return getHospitalByType(hospitalType);
        }
        //3. 시도 조건만 검색
        else if(hospitalName == null && hospitalType == null && siDo != null){
            return getHospitalBySido(siDo);
        }
        //4. 이름 조건만 검색
        else if(hospitalName != null && hospitalType == null && siDo == null){
            return getHospitalByName(hospitalName);
        }
        //5. 병원타입 , 시도 조건 검색
        else if(hospitalName == null && hospitalType != null && siDo != null){
            return getHospitalsByTypeAndSido(hospitalType,siDo);
        }
        //6. 병원타입 , 병원이름 조건 검색
        else if(hospitalName != null && hospitalType != null && siDo == null){
            return getHospitalsByNameAndType(hospitalName,hospitalType);
        }
        //7. 병원이름, 시도 조건 검색
        else if(hospitalName != null && hospitalType == null && siDo != null){
            return getHospitalsByNameAndSido(hospitalName,siDo);
        }
        //8. 모든조건을 다 넣었을때 검색
        else{
            return getHospitalsByNameAndTypeAndSido(hospitalType,hospitalName,siDo);
        }
    }

    //페이징처리 메서드
    //첫 번째 조회결과를 캐싱처리하고, currentPage 와 perPage 가 변경 될 때는 캐시에서 데이터를 가져와 페이징처리
    public Page<HospitalResponseDto> getHospitalPaged(List<HospitalResponseDto> dtoList,Integer currentPage, Integer perPage){
        Pageable pageable = PageRequest.of(currentPage, perPage, Sort.by("id"));
        Page<HospitalResponseDto> hospitalPage = new PageImpl<>(
                dtoList.subList((currentPage*10),(currentPage*10)+perPage),
                pageable,
                dtoList.size());
        return hospitalPage;
    }

}





//병원타입 , 시도 동시에 매핑
//제일 많이 검색한 키워드
//댓글
//최근 작성한 댓글
//대댓글
//조회수 많은 병원
//조회수
//병원점수 5점만점