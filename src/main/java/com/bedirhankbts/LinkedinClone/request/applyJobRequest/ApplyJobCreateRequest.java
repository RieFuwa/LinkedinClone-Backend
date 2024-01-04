package com.bedirhankbts.LinkedinClone.request.applyJobRequest;

import lombok.Data;

@Data
public class ApplyJobCreateRequest {

    private Long id;
    private Long userId;
    private Long jobId;
}
