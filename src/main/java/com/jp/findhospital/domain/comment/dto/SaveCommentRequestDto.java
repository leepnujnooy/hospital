package com.jp.findhospital.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class SaveCommentRequestDto {
    private Long hospitalId;
    private String text;
    private Integer score;
}
