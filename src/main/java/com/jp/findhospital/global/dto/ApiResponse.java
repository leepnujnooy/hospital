package com.jp.findhospital.global.dto;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

//ResponseEntity 구현체 
public class ApiResponse<T> extends ResponseEntity<T> {
    //메서드 재정의
    public ApiResponse(HttpStatus httpStatus, T data){
        super(data,httpStatus);
    }

}
// ResponseEntity => ApiResponse 로 교체하는 작업 시작
