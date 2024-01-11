package com.jp.findhospital.domain.comment.service;

import com.jp.findhospital.domain.comment.dto.SaveCommentRequestDto;
import com.jp.findhospital.domain.comment.entity.Comment;
import com.jp.findhospital.domain.comment.repository.CommentRepository;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final HospitalRepository hospitalRepository;


    public void saveComment(Long hospitalId, SaveCommentRequestDto commentDto){
        //병원존재유무
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(RuntimeException::new);

        //저장
        commentRepository.save(Comment.builder()
                        .text(commentDto.getText())
                        .hospital(hospital)
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .build());
    }


    public List<Comment> getCommentAll(Long id){
        //hospitalId 로 찾기
        List<Comment> commentList = commentRepository.findAllByHospitalId(id);

        if(commentList.isEmpty()) throw new RuntimeException();

        return commentList;
    }

}
