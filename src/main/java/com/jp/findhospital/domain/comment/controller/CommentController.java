package com.jp.findhospital.domain.comment.controller;

import com.jp.findhospital.domain.comment.dto.CommentResponseDto;
import com.jp.findhospital.domain.comment.dto.DeleteCommentRequestDto;
import com.jp.findhospital.domain.comment.dto.SaveCommentRequestDto;
import com.jp.findhospital.domain.comment.entity.Comment;
import com.jp.findhospital.domain.comment.service.CommentService;
import com.jp.findhospital.global.dto.ApiResponse;
import com.jp.findhospital.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    //댓글 달기
    @PostMapping("/hospital/{hospitalId}/comment")
    public ApiResponse<ResponseDto> createComment(
            @PathVariable("hospitalId") Long hospitalId,
            @RequestBody SaveCommentRequestDto saveCommentRequestDto){
        commentService.saveComment(hospitalId,saveCommentRequestDto);
        return new ApiResponse<>(HttpStatus.OK,ResponseDto.builder().message("리뷰 등록완료").build());
    }

    //댓글 삭제
    @DeleteMapping("/hospital/{hospitalId}/comment/{commentId}")
    public ApiResponse<ResponseDto> deleteComment(
            @PathVariable("hospitalId") Long hospitalId,
            @PathVariable("commentId") Long commentId,
            @RequestBody DeleteCommentRequestDto dto){
        log.info("리뷰 삭제요청");
        commentService.deleteComment(hospitalId,commentId, dto);
        return new ApiResponse<>(HttpStatus.OK,ResponseDto.builder().message("리뷰 삭제완료").build());
    }

    //댓글 전체보기
    @GetMapping("/hospital/{hospitalId}/comment")
    public ApiResponse<List<CommentResponseDto>> getCommentAll(@PathVariable("hospitalId")Long id){
        List<CommentResponseDto> commentList = commentService.getCommentAll(id);
        return new ApiResponse<>(HttpStatus.OK,commentList);
    }

    //댓글 최신순으로 5개 불러오기
    @GetMapping("/recentcomment")
    public ApiResponse<List<CommentResponseDto>> getRecentComment(){
        return new ApiResponse<>(HttpStatus.OK,commentService.getRecentComment());
    }


}
