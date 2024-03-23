package com.jp.findhospital.domain.hospital.dto;

import com.jp.findhospital.domain.hospital.entity.Hospital;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalResponseDto {
    private Long id;
    private String hospitalName;
    private String hospitalType;
    private String siDo;
    private String gunGu;
    private Integer mailNumber;
    private String address;
    private String hospitalPhoneNumber;
    private String hospitalStartDate;
    private Float x;
    private Float y;
    private Integer hits;
    private Float score;

    //엔티티에서 dto 변환시 사용하기
    public static HospitalResponseDto entityToDto(Hospital hospital){
        HospitalResponseDto responseDto = new HospitalResponseDto();
        responseDto.setId(hospital.getId());
        responseDto.setHospitalName(hospital.getHospitalName());
        responseDto.setAddress(hospital.getAddress());
        responseDto.setHospitalType(hospital.getHospitalType());
        responseDto.setSiDo(hospital.getSiDo());
        responseDto.setGunGu(hospital.getGunGu());
        responseDto.setX(hospital.getX());
        responseDto.setY(hospital.getY());
        responseDto.setHospitalStartDate(hospital.getHospitalStartDate());
        responseDto.setHospitalPhoneNumber(hospital.getHospitalPhoneNumber());
        responseDto.setMailNumber(hospital.getMailNumber());
        responseDto.setHits(hospital.getHits());
        responseDto.setScore(hospital.getScore());

        return responseDto;
    }
}
