package com.bedirhankbts.LinkedinClone.dto.jobDto;

import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.model.JobType;
import lombok.Data;

import java.util.Date;

@Data
public class JobGetDto {
    private Long id;
    private JobType jobType;
    private String jobDetails;
    private Long companyId;
    private String companyName;
    private Date createDate;

    public JobGetDto(Job entity){
        this.id=entity.getId();
        this.jobType=entity.getJobType();
        this.companyId=entity.getCompany().getId();
        this.companyName=entity.getCompany().getCompanyName();
        this.jobDetails=entity.getJobDetails();
        this.createDate=entity.getCreateDate();
    }
}
