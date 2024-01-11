package com.jp.findhospital.global.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ResponseDto {
    private String message;
    private Timestamp timestamp;
}
