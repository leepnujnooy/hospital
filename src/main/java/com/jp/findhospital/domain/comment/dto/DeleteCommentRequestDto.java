package com.jp.findhospital.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteCommentRequestDto {

    private Long hospitalId;
    private String password;

}
