package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobUpdateDto;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobUpdateRequest;
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

    ResponseEntity<JobUpdateDto> jobUpdateByJobId(Long jobId, JobUpdateRequest updateJob);

    List<JobJobTypeCount> getJobTypeByCount();

    List<JobJobTypeCount> getJobTypeByCountCompany(Long companyId);

    List<JobApplyCount> getJobApplyByCountCompany(Long companyId);

    Long countByJobByCompany(Long companyId);

}
