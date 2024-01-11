package com.jp.findhospital.domain.hospital.entity;

import com.jp.findhospital.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name="si_gun_gu")
    private String siGunGu;

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

    @OneToMany(mappedBy = "hospital" , fetch = FetchType.LAZY)
    private List<Comment> commentList;
}
