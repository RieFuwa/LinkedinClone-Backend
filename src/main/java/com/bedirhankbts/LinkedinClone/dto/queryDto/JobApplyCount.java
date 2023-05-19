package com.bedirhankbts.LinkedinClone.dto.queryDto;

import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import com.bedirhankbts.LinkedinClone.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplyCount {
    private Long jobId;
    private Long applyJobId;
    private String jobTypeName;
    private Long countTitle;

    public JobApplyCount(ApplyJob entity){
        this.jobId=entity.getJob().getId();
        this.applyJobId=entity.getId();
        this.jobTypeName=entity.getJob().getJobType().getJobTypeName();
        this.countTitle=getCountTitle();

    }
}
