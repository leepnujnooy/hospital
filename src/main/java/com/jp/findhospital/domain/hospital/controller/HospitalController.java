package com.jp.findhospital.domain.hospital.controller;


import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.domain.hospital.dto.HospitalResponseDto;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import com.jp.findhospital.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final CommentService commentService;

    //전체 병원 조회
    //수정 : List => Page 형태로 변환
//    @GetMapping("/hospitals")
//    public ApiResponse<Page<HospitalResponseDto>> getHospitalAllByPage(
//            @RequestParam(name = "currentPage", defaultValue = "0") Integer currentPage,
//            @RequestParam(name = "perPage", defaultValue = "10") Integer perPage
//    ){
//        return new ApiResponse<>(HttpStatus.OK,hospitalService.getHospitalPaged(currentPage,perPage));
//    }

    //병원 필터링 메서드
    @GetMapping("/hospitals/condition")
    public ApiResponse<Page<HospitalResponseDto>> getHospitalFilteredByCondition(
            @RequestParam(value = "hospitalType", required = false) String hospitalType,
            @RequestParam(value = "hospitalName", required = false) String hospitalName,
            @RequestParam(value = "siDo", required = false) String siDo,
            @RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") Integer perPage
    ){
        if(hospitalName == "") hospitalName = null;
        if(hospitalType == "") hospitalType = null;
        if(siDo == "") siDo = null;

        List<HospitalResponseDto> responseDtoList =
                hospitalService.getHospitals(hospitalType,hospitalName,siDo);


        Page<HospitalResponseDto> responseDtoPage = hospitalService.getHospitalPaged(responseDtoList,currentPage,perPage);

        return new ApiResponse<>(HttpStatus.OK,responseDtoPage);
    }


    //단일 병원 페이지 이동
    @GetMapping("/hospital/{id}")
    public ApiResponse<HospitalResponseDto> getHospital(@PathVariable("id") Long id){
        HospitalResponseDto dto = hospitalService.getHospitalById(id);
        hospitalService.updateCache(id,dto);
        return new ApiResponse<>(HttpStatus.OK,dto);
    }


//    //단일 병원 검색 (이름으로)
//    @GetMapping("/search/name")
//    public ApiResponse<List<HospitalResponseDto>> getHospitalFilteredByName(
//            @RequestParam String name){
//
//        return new ApiResponse<>(HttpStatus.OK,new ArrayList<>());
//    }
//
//
//    //시도별 병원 검색
//    @GetMapping("/search/county")
//    public ApiResponse<List<Hospital>> getHospitalFilteredByCounty(@RequestParam String county){
//        List<Hospital> hospitalList = hospitalService.getHospitalBySido(county);
//
//        return new ApiResponse<>(HttpStatus.OK,hospitalList);
//    }
//
//    //시군구별 병원 검색
//    @GetMapping("/search/district")
//    public ApiResponse<List<Hospital>> getHospitalFilteredByCity(@RequestParam String district){
//        List<Hospital> hospitalList = hospitalService.getHospitalByGunGu(district);
//
//        return new ApiResponse<>(HttpStatus.OK,hospitalList);
//    }
//
//    //병원타입별 병원 검색
//    @GetMapping("/search/type")
//    public ApiResponse<List<Hospital>> getHospitalFilteredByType(@RequestParam String type){
//        List<Hospital> hospitalList = hospitalService.getHospitalByType(type);
//
//        return new ApiResponse<>(HttpStatus.OK,hospitalList);
//    }

}

//    쿼리 결과의 범위 지정: 데이터베이스 쿼리에서 페이징을 지원하는 경우,
//    다음 페이지 요청 시에는 이전 페이지의 결과 범위를 지정하여 다음 페이지의 결과를 가져옵니다.
//    이를 통해 데이터베이스는 해당 범위의 결과만 가져와서 처리하므로 효율적입니다.
