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

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final CommentService commentService;

    //전체 병원 조회
    //수정 : List => Page 형태로 변환
    @GetMapping("/hospitals")
    public ApiResponse<Page<HospitalResponseDto>> getHospitalAllByPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ){

        return new ApiResponse<>(HttpStatus.OK,hospitalService.getHospitalPaged(page,limit));
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

//    페이징 처리는 주로 서버 측에서 처리하는 것이 일반적으로 더 좋습니다.
//    그 이유는 다음과 같습니다:
//
//        데이터 양 관리: 대부분의 경우, 서버는 모든 데이터를 가지고 있으며,
//        페이징 처리를 통해 필요한 만큼의 데이터만 클라이언트로 전달할 수 있습니다. 클라이언트에서 모든 데이터를 로드하고 페이징 처리를 수행하면
//        불필요한 대량한 데이터 양이 네트워크를 통해 전송되고, 이는 성능 문제를 초래할 수
//        있습니다.
//
//        보안: 클라이언트에서 페이징 처리를 수행하면 전체 데이터가 클라이언트로 전송될 가능성이
//        있으며, 이는 보안상의 위험을 초래할 수 있습니다. 서버 측에서 페이징 처리를 수행하면 클라이언트는
//        필요한 데이터만 받아오고, 민감한 데이터에 대한 접근을 제한할 수 있습니다.
//
//        검색 엔진 최적화(SEO): 서버 측에서 페이징 처리를 수행하면 검색 엔진이 페이지의
//        내용을 쉽게 색인화할 수 있습니다.
//        반면 클라이언트 측에서 페이징 처리를 수행하면 검색 엔진이 페이지의 내용을
//        인식하기 어려울 수 있습니다.
//
//        React에서도 페이징 처리를 수행할 수 있지만, 이는 주로 사용자 경험을 향상시키기 위한 것이며, 서버에서 데이터를 가져오고 페이징 처리를 수행하는 것이 더 효율적입니다. 따라서 일반적으로 페이징 처리는 서버 측에서 구현하고, React나 다른 클라이언트 측 프레임워크에서는 서버로부터 제공된 데이터를 표시하는 것에 집중하는 것이 좋습니다.

//todo 병원 페이징 처리