package com.jp.findhospital.domain.hospital.controller;


import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import com.jp.findhospital.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final CommentService commentService;

    //전체 병원 조회
    @GetMapping("/hospitals")
    public ApiResponse<List<Hospital>> getHospitalAll(){

        List<Hospital> hospitalList = hospitalService.getHospitalAll();

        return new ApiResponse<>(HttpStatus.OK,hospitalList);
    }

    //단일 병원 조회
    @GetMapping("/hospital/{id}")
    public ApiResponse<Hospital> getHospital(@PathVariable("id") Long id){

        Hospital hospital = hospitalService.getHospitalById(id);

        return new ApiResponse<>(HttpStatus.OK,hospital);
    }


    //단일 병원 검색 (이름으로)
    @GetMapping("/search/name")
    public ApiResponse<List<Hospital>> getHospitalByName(@RequestParam String name){
        List<Hospital> hospitalList = hospitalService.getHospital(name);

        return new ApiResponse<>(HttpStatus.OK,hospitalList);
    }


    //시도별 병원 검색
    @GetMapping("/search/province")
    public ApiResponse<List<Hospital>> getHospitalByProvince(@RequestParam String province){
        List<Hospital> hospitalList = hospitalService.getHospitalBySido(province);

        return new ApiResponse<>(HttpStatus.OK,hospitalList);
    }

    //시군구별 병원 검색
    @GetMapping("/search/city")
    public ApiResponse<List<Hospital>> getHospitalByCity(@RequestParam String city){
        List<Hospital> hospitalList = hospitalService.getHospitalBySiGunGu(city);

        return new ApiResponse<>(HttpStatus.OK,hospitalList);
    }

    //병원타입별 병원 검색
    @GetMapping("/search/type")
    public ApiResponse<List<Hospital>> getHospitalByType(@RequestParam String type){
        List<Hospital> hospitalList = hospitalService.getHospitalByType(type);

        return new ApiResponse<>(HttpStatus.OK,hospitalList);
    }





}
