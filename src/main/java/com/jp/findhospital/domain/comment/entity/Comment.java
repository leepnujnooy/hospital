package com.jp.findhospital.domain.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @Column
    private Timestamp timestamp;


    //comment 검색시 hospital 은 나오지않게 ignore
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
