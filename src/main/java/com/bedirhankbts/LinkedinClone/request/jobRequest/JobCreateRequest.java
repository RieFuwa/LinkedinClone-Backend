package com.bedirhankbts.LinkedinClone.request.jobRequest;

import lombok.Data;

@Data
public class JobCreateRequest {
    private Long id;
    private Long jobTypeId;
    private Long companyId;
    private String JobDetails;
}
