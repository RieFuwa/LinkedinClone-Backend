package com.bedirhankbts.LinkedinClone.dto.jobDto;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.model.JobType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobGetDto {
    private Long id;
    private JobType jobType;
    private String jobDetails;
    private Long companyId;
    private String companyName;

    private Date createDate;
    private List<ApplyJobDto> applyJobList;

    public JobGetDto(Job entity, List<ApplyJobDto> applyJobList){
        this.id=entity.getId();
        this.jobType=entity.getJobType();
        this.companyId=entity.getCompany().getId();
        this.companyName=entity.getCompany().getCompanyName();
        this.jobDetails=entity.getJobDetails();
        this.createDate=entity.getCreateDate();
        this.applyJobList=applyJobList;
    }
}
