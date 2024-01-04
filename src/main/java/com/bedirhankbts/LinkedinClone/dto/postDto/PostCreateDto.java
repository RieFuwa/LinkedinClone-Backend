package com.bedirhankbts.LinkedinClone.dto.postDto;

import lombok.Data;

@Data
public class PostCreateDto {
    private String message;
    private Long userId;
    private Long postId;
}
