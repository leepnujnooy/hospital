package com.jp.findhospital.domain.comment.dto;

import com.jp.findhospital.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponseDto {
    private Long hospitalId;
    private Long commentId;
    private String text;
    private Integer score;

    public static CommentResponseDto entityToDto(Comment comment){
        return CommentResponseDto
                .builder()
                .hospitalId(comment.getHospital().getId())
                .commentId(comment.getId())
                .text(comment.getText())
                .score(comment.getScore())
                .build();
    }
}
