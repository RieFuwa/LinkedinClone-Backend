package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface JobService {
    ResponseEntity<JobCreateDto> createJob(JobCreateRequest jobCreateRequest);

    List<JobGetDto> getAllJob();

    String deleteJobById(Long jobId);

    List<JobGetDto> getAllCompanyJob(Optional<Long> companyId);

    Job getJobById(Long jobId);

    JobGetDto getOneJobWithParameters(Long jobId);

    List<JobGetDto> getJobByJobTypeId(Optional<Long> jobTypeId);
}
