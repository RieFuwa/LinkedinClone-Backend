package com.bedirhankbts.LinkedinClone.dto.applyJobDto;

import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import com.bedirhankbts.LinkedinClone.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class ApplyJobDto {
    private Long id;
    private User user;
    private Long jobId;
    private Date createDate;
    private String companyName;
    private String jobTypeName;
    public ApplyJobDto(ApplyJob entity){
        this.id=entity.getId();
        this.jobTypeName=entity.getJob().getJobType().getJobTypeName();
        this.companyName=entity.getJob().getCompany().getCompanyName();
        this.createDate=entity.getCreateDate();
        this.user=entity.getUser();
        this.jobId=entity.getJob().getId();
    }
}
