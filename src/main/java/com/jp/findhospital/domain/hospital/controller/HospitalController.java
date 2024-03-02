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
        log.info("요청 들어옴");
        if(hospitalName == "") hospitalName = null;
        if(hospitalType == "") hospitalType = null;
        if(siDo == "") siDo = null;

        Page<HospitalResponseDto> responseDtoPage =
                hospitalService.getHospitals(hospitalType,hospitalName,siDo,currentPage,perPage);

        return new ApiResponse<>(HttpStatus.OK,responseDtoPage);
    }


    //단일 병원 페이지 이동
    @GetMapping("/hospital/{id}")
    public ApiResponse<HospitalResponseDto> getHospital(@PathVariable("id") Long id){
        return new ApiResponse<>(HttpStatus.OK,hospitalService.getHospitalById(id));
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


//    캐싱 사용: 검색 결과를 캐싱하여 다음 페이지 요청 시에 캐시된 데이터를 사용합니다.
//    이를 통해 매번 데이터베이스에 접근하지 않고도 다음 페이지를 빠르게 반환할 수 있습니다. 하지만 캐싱은 항상 최신 데이터를 보장하지 않으므로 신중하게 사용해야 합니다.
//
//    검색 결과를 한 번에 가져오기: 클라이언트가 처음 검색을 요청할 때 모든 검색 결과를 한 번에 가져옵니다.
//    이 경우에는 처음 요청 시에는 데이터베이스에 부하가 많이 가겠지만, 이후에는 페이징 없이 빠르게 다음 페이지를 제공할 수 있습니다.
//    하지만 결과 집합이 큰 경우에는 이 방법도 비효율적일 수 있습니다.
//
//    쿼리 결과의 범위 지정: 데이터베이스 쿼리에서 페이징을 지원하는 경우,
//    다음 페이지 요청 시에는 이전 페이지의 결과 범위를 지정하여 다음 페이지의 결과를 가져옵니다.
//    이를 통해 데이터베이스는 해당 범위의 결과만 가져와서 처리하므로 효율적입니다.
//
//    쿼리 최적화: 데이터베이스 쿼리를 최적화하여 페이징 쿼리의 성능을 향상시킵니다.
//    예를 들어, 인덱스를 적절히 사용하거나 쿼리를 튜닝하여 처리 속도를 개선할 수 있습니다.
