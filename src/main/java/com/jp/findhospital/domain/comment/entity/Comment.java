package com.jp.findhospital.domain.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import javax.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Integer score;

    @Column
    private String password;

    @Column
    private LocalDateTime dateTime;


    //comment 검색시 hospital 은 나오지않게 ignore
    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
