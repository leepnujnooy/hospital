package com.jp.findhospital.domain.comment.controller;

import com.jp.findhospital.domain.comment.dto.SaveCommentRequestDto;
import com.jp.findhospital.domain.comment.entity.Comment;
import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    //댓글 달기
    @PostMapping("/hospital/{hospitalId}/comment")
    public ResponseEntity<ResponseDto> createComment(
            @PathVariable("hospitalId") Long hospitalId,
            @RequestBody SaveCommentRequestDto saveCommentRequestDto){

        //댓글 저장
        commentService.saveComment(hospitalId,saveCommentRequestDto);

        //응답 리턴
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("댓글 작성 완료");
        responseDto.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.status(200).body(responseDto);
    }

    //댓글 전체보기
    @GetMapping("/hospital/{hospitalId}/comments")
    public ResponseEntity<List<Comment>> getCommentAll(@PathVariable("hospitalId")Long id){
        List<Comment> commentList = commentService.getCommentAll(id);
        return ResponseEntity.status(200).body(commentList);
    }

}
