package com.jp.findhospital.domain.hospital.service;

import com.jp.findhospital.domain.hospital.dto.HospitalResponseDto;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
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

    //id로 단일 병원 조회
    public HospitalResponseDto getHospitalById(Long id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");
        return HospitalResponseDto.entityToDto(optionalHospital.get());
    }

    //병원 전체조회
    public Page<HospitalResponseDto> getHospitalAllWithPaged(Integer currentPage, Integer perPage){

        //페이지 request 를 정의
        Pageable pageable = PageRequest.of(currentPage, perPage, Sort.by("id"));
        Page<Hospital> hospitalPage = hospitalRepository.findAll(pageable);

        return hospitalPage.map(HospitalResponseDto::entityToDto);
    }

    //병원이름으로 조회
    public Page<HospitalResponseDto> getHospitalByName(String hospitalName){
        Optional<List<Hospital>> optionalHospital = hospitalRepository.findByHospitalName(hospitalName);
//        if(optionalHospital.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        // currentPage, perPage 추가 구현
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Hospital> hospitalPage = new PageImpl<>(
                optionalHospital.get(),
                pageable,
                optionalHospital.get().size());

        return hospitalPage.map(HospitalResponseDto::entityToDto);
    }

    //시도 병원 조회
    public Page<HospitalResponseDto> getHospitalBySido(String siDo){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findAllBySiDoContaining(siDo);
//        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        // currentPage, perPage 추가 구현
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Hospital> hospitalPage = new PageImpl<>(
                optionalHospitals.get(),
                pageable,
                optionalHospitals.get().size());

        return hospitalPage.map(HospitalResponseDto::entityToDto);
    }


    //병원타입 필터만 거친 병원 조회
    public Page<HospitalResponseDto> getHospitalByType(String hospitalType){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findAllByHospitalType(hospitalType);
//        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        // currentPage, perPage 추가 구현
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Hospital> hospitalPage = new PageImpl<>(
                optionalHospitals.get(),
                pageable,
                optionalHospitals.get().size());

        return hospitalPage.map(HospitalResponseDto::entityToDto);
    }

    //2개이상의 조건이 붙은 병원 조회
    public Page<HospitalResponseDto> getHospitalByCondition(
            String hospitalName,
            String hospitalType,
            String siDo){
        Optional<List<Hospital>> optionalHospitals = hospitalRepository.findCriteria(hospitalName,hospitalType,siDo);
//        if(optionalHospitals.isEmpty()) throw new RuntimeException("HOSPITAL DOESN'T EXISTS");

        // currentPage, perPage 추가 구현
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Hospital> hospitalPage = new PageImpl<>(
                optionalHospitals.get(),
                pageable,
                optionalHospitals.get().size());

        return hospitalPage.map(HospitalResponseDto::entityToDto);
    }


    //조회 후 조건에 맞는 병원리스트 간추리는 메서드
    public Page<HospitalResponseDto> getHospitals(
            String hospitalType,
            String hospitalName,
            String siDo
    ){

        //경우의 수 =>
        //1. 아무런 조건 없이 검색
        if(hospitalName == null && hospitalType == null && siDo == null){
            return getHospitalAllWithPaged(0,10);
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
        //6. 병원타입 , 이름 조건 검색
        //7. 이름, 시도 조건 검색
        else{
            return getHospitalByCondition(hospitalName,hospitalType,siDo);
//            return new PageImpl<>(new ArrayList<>());
        }
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





//
//        데이터 전달의 명확성과 일관성: DTO를 사용하면 데이터 전달이 명확하고 일관성 있게 이루어집니다.
//        DTO는 특정 요청 또는 응답에 필요한 데이터만을 포함하므로,
//        데이터 전달이 명시적이고 불필요한 정보가 제거됩니다.
//
//        클라이언트-서버 간 데이터 전송: 웹 애플리케이션에서 클라이언트와 서버 간의 데이터 전송이 필요한 경우,
//        DTO를 사용하여 데이터를 캡슐화하고 전송할 수 있습니다.
//        이는 네트워크 트래픽을 줄이고 데이터 전송 속도를 향상시킬 수 있습니다.
//
//        레이어 간의 분리: DTO를 사용하면 서비스 레이어와 프레젠테이션 레이어 사이에 데이터 모델을 분리할 수 있습니다.
//        이를 통해 각 레이어가 서로 독립적으로 변경될 수 있으며, 시스템의 유지보수성과 확장성이 향상됩니다.
//
//        데이터 포맷 변환: DTO를 사용하면 서로 다른 데이터 모델 간의 변환 작업을 수행할 수 있습니다.
//        예를 들어, 클라이언트 측에서는 JSON 형식의 DTO를 사용하고,
//        서버 측에서는 데이터베이스의 엔터티를 사용할 수 있습니다. 이렇게 하면 데이터를 필요한 형식으로 변환할 수 있습니다.