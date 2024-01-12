package com.jp.findhospital.domain.hospital.controller;


import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<Hospital>> getHospitalAll(){

        List<Hospital> hospitalList = hospitalService.getHospitalAll();

        return ResponseEntity.status(200).body(hospitalList);
    }

    //단일 병원 조회
    @GetMapping("/hospital/{id}")
    public ResponseEntity<Hospital> getHospital(@PathVariable("id") Long id){

        Hospital hospital = hospitalService.getHospitalById(id);

        return ResponseEntity.status(200).body(hospital);
    }


    //단일 병원 검색 (이름으로)
    @GetMapping("/search/name")
    public ResponseEntity<List<Hospital>> getHospitalByName(@RequestParam String name){
        List<Hospital> hospitalList = hospitalService.getHospital(name);

        return ResponseEntity.status(200).body(hospitalList);
    }


    //시도별 병원 검색
    @GetMapping("/search/province")
    public ResponseEntity<List<Hospital>> getHospitalByProvince(@RequestParam String province){
        List<Hospital> hospitalList = hospitalService.getHospitalBySido(province);

        return ResponseEntity.status(200).body(hospitalList);
    }

    //시군구별 병원 검색
    @GetMapping("/search/city")
    public ResponseEntity<List<Hospital>> getHospitalByCity(@RequestParam String city){
        List<Hospital> hospitalList = hospitalService.getHospitalBySiGunGu(city);

        return ResponseEntity.status(200).body(hospitalList);
    }

    //병원타입별 병원 검색
    @GetMapping("/search/type")
    public ResponseEntity<List<Hospital>> getHospitalByType(@RequestParam String type){
        List<Hospital> hospitalList = hospitalService.getHospitalByType(type);

        return ResponseEntity.status(200).body(hospitalList);
    }





}
