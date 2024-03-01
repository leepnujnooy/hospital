package com.jp.findhospital.domain.comment.service;

import com.jp.findhospital.domain.comment.dto.SaveCommentRequestDto;
import com.jp.findhospital.domain.comment.entity.Comment;
import com.jp.findhospital.domain.comment.repository.CommentRepository;
import com.jp.findhospital.domain.hospital.entity.Hospital;
import com.jp.findhospital.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final HospitalRepository hospitalRepository;


    @Transactional
    public void saveComment(Long hospitalId, SaveCommentRequestDto commentDto){
        //병원존재유무
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(RuntimeException::new);

        //댓글위치 == 병원위치 다르면 예외처리
        if(!hospitalId.equals(commentDto.getHospitalId())) throw new RuntimeException();


        //병원점수 업데이트하기
        float newScore = commentDto.getScore();
        List<Comment> commentList = getCommentAll(hospitalId);
        //만약 댓글이 한개도 없다면 ==> 첫번째 점수
        if(commentList.isEmpty()){
            hospitalRepository.save(Hospital.builder()
                            .id(hospital.getId())
                            .hospitalName(hospital.getHospitalName())
                            .hospitalType(hospital.getHospitalType())
                            .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                            .hospitalStartDate(hospital.getHospitalStartDate())
                            .address(hospital.getAddress())
                            .siDo(hospital.getSiDo())
                            .gunGu(hospital.getGunGu())
                            .mailNumber(hospital.getMailNumber())
                            .hits(hospital.getHits())
                            .score(newScore)
                            .x(hospital.getX())
                            .y(hospital.getY())
                            .build()
            );
        }
        //아니라면
        else{
            // 스코어점수 = (현재까지스코어 * 댓글개수 + 새로운스코어) / (댓글개수 + 1)
            float score =(float)(((hospital.getScore() * commentList.size()) + newScore) / (commentList.size() + 1));
            float roundedScore =(float)(Math.round(score * 10.0) / 10.0);
            hospitalRepository.save(Hospital.builder()
                    .id(hospital.getId())
                    .hospitalName(hospital.getHospitalName())
                    .hospitalType(hospital.getHospitalType())
                    .hospitalPhoneNumber(hospital.getHospitalPhoneNumber())
                    .hospitalStartDate(hospital.getHospitalStartDate())
                    .address(hospital.getAddress())
                    .siDo(hospital.getSiDo())
                    .gunGu(hospital.getGunGu())
                    .mailNumber(hospital.getMailNumber())
                    .hits(hospital.getHits())
                    .score(roundedScore)
                    .x(hospital.getX())
                    .y(hospital.getY())
                    .build()
            );
        }

        //저장
        commentRepository.save(Comment.builder()
                .text(commentDto.getText())
                .hospital(hospital)
                .score(commentDto.getScore())
                .dateTime(LocalDateTime.now())
                .build());

    }


    @Transactional(readOnly = true)
    public List<Comment> getCommentAll(Long id){
        //hospitalId 로 찾기
        List<Comment> commentList = commentRepository.findAllByHospitalId(id);
        return commentList;
    }

}
