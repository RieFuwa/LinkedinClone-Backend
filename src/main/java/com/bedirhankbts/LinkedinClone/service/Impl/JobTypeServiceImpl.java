package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.model.JobType;
import com.bedirhankbts.LinkedinClone.repository.JobTypeRepository;
import com.bedirhankbts.LinkedinClone.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeServiceImpl implements JobTypeService {

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public JobType createJobType(JobType newJobType) {
       return jobTypeRepository.save(newJobType);
    }

    @Override
    public List<JobType> getAllJobType() {
        return jobTypeRepository.findAll();
    }

    @Override
    public JobType getJobTypeById(Long jobTypeId) {
        return jobTypeRepository.findById(jobTypeId).orElse(null);
    }

    @Override
    public String deleteCompanyTypeById(Long jobTypeId) {
        if (!jobTypeRepository.existsById(jobTypeId)) {
            return "JobType with id not found " +jobTypeId+".";
        }
        jobTypeRepository.deleteById(jobTypeId);
        return "JobType with id " +jobTypeId+ " has been deleted success.";
    }
}
