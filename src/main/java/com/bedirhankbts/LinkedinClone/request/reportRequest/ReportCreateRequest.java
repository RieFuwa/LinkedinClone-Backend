package com.bedirhankbts.LinkedinClone.request.reportRequest;

import lombok.Data;

@Data
public class ReportCreateRequest {
    private Long id ;
    private Long userId;
    private Long postId;
    private String reportText;

}
