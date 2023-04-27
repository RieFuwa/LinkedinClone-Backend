package com.bedirhankbts.LinkedinClone.dto.applyJobDto;

import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import lombok.Data;

@Data
public class ApplyJobDto {
    private Long id;
    private Long userId;
    private Long jobId;
    public ApplyJobDto(ApplyJob entity){
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.jobId=entity.getJob().getId();
    }
}
