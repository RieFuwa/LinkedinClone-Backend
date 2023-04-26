package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.model.JobType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobTypeService {
    JobType createJobType(JobType newJobType);

    List<JobType> getAllJobType();

    JobType getJobTypeById(Long jobTypeId);

    String deleteCompanyTypeById(Long jobTypeId);
}
