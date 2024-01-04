package com.bedirhankbts.LinkedinClone.request.postRequest;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long id;
    private Long userId;
    private Long connectedPostId;
    private String postTitle;
    private String postText;
}
