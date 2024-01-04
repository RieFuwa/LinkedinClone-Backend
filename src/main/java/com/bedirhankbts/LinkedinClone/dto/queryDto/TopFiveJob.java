package com.bedirhankbts.LinkedinClone.dto.queryDto;

import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopFiveJob {

    private Long jobId;
    private Long applyJobId;

    private String  jobTypeName;
    private String companyName;
    private Long countTitle;

    public TopFiveJob(ApplyJob entity){
        this.jobId=entity.getJob().getId();
        this.applyJobId=entity.getId();
        this.jobTypeName=entity.getJob().getJobType().getJobTypeName();
        this.companyName=entity.getJob().getCompany().getCompanyName();
        this.countTitle=getCountTitle();


    }
}
