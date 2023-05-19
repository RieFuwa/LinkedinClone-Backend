package com.bedirhankbts.LinkedinClone.dto.queryDto;

import com.bedirhankbts.LinkedinClone.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobJobTypeCount {
    private String  jobTypeName;
    private Long jobTypeId;
    private Long countTitle;

    public JobJobTypeCount(Job entity){
        this.jobTypeName=entity.getJobType().getJobTypeName();
        this.jobTypeId=entity.getJobType().getId();
        this.countTitle=getCountTitle();

    }
}
