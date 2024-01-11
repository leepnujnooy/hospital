package com.jp.findhospital.domain.hospital.controller;


import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final CommentService commentService;

    //전체 병원 조회
    @GetMapping("/search")
    public ResponseEntity<List<Hospital>> getHospitalAll(){

        List<Hospital> hospitalList = hospitalService.getHospitalAll();

        return ResponseEntity.status(200).body(hospitalList);
    }

    //단일 병원 조회 (id로)
    @GetMapping("/search/{id}")
    public ResponseEntity<Hospital> getHospital(@PathVariable("id") Long id){

        Hospital hospital = hospitalService.getHospitalById(id);

        return ResponseEntity.status(200).body(hospital);
    }

    //시도별 병원 조회






}
