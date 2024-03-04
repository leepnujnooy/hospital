package com.jp.findhospital.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
    private String message;

    public static ResponseDto getInstance(String message){
        ResponseDto responseDto = new ResponseDto(message);
        return responseDto;
    }
}
