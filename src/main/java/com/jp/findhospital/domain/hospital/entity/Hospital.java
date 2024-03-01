package com.jp.findhospital.domain.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jp.findhospital.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hospital")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hospital_name")
    private String hospitalName;

    @Column(name="hospital_type")
    private String hospitalType;

    @Column(name="si_do")
    private String siDo;

    @Column(name="gun_gu")
    private String gunGu;

    @Column(name="mail_number")
    private Integer mailNumber;

    @Column(name="address")
    private String address;

    @Column(name="hospital_phone_number")
    private String hospitalPhoneNumber;

    @Column(name="hospital_start_date")
    private String hospitalStartDate;

    @Column(name="x")
    private Float x;

    @Column(name="y")
    private Float y;

    @Column(name="hits" , columnDefinition = "INT DEFAULT 0")
    private Integer hits;

    @Column(name = "score" , columnDefinition = "FLOAT DEFAULT 0")
    private Float score;

    public void updateHits(){
        this.hits = hits + 1;
    }

    //jsonIgnore == hospital 단일 조회시 json 형식의 comment 는 보지 않기 위한 어노테이션
    //@JsonIgnore
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Comment> commentList;
}
