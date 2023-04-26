package com.bedirhankbts.LinkedinClone.service.Impl;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.*;
import com.bedirhankbts.LinkedinClone.repository.JobRepository;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
import com.bedirhankbts.LinkedinClone.service.CompanyService;
import com.bedirhankbts.LinkedinClone.service.JobService;
import com.bedirhankbts.LinkedinClone.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobTypeService jobTypeService;

    @Autowired
    private CompanyService companyService;

    @Override
    public ResponseEntity<JobCreateDto> createJob(JobCreateRequest jobCreateRequest) {
        JobCreateDto jobCreateDto = new JobCreateDto();
        JobType jobType = jobTypeService.getJobTypeById(jobCreateRequest.getJobTypeId());
        Company company = companyService.getCompanyById(jobCreateRequest.getCompanyId());
        if(jobType==null && company==null) {
            jobCreateDto.setMessage("Job not created. JobTypeId or CompanyId not found.");
            return new ResponseEntity<>(jobCreateDto, HttpStatus.BAD_REQUEST);
        }
        Job toCreate = new Job();
        toCreate.setId(jobCreateRequest.getId());
        toCreate.setJobType(jobType);
        toCreate.setCompany(company);
        toCreate.setJobDetails(jobCreateRequest.getJobDetails());
        toCreate.setCreateDate(new Date());
        jobRepository.save(toCreate);
        jobCreateDto.setMessage("Job successfully created.");
        jobCreateDto.setJobId(toCreate.getId());
        jobCreateDto.setCompanyId(toCreate.getCompany().getId());
        return new ResponseEntity<>(jobCreateDto, HttpStatus.CREATED);
    }

    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public String deleteJobById(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            return "Job with id not found " +jobId+".";
        }
        jobRepository.deleteById(jobId);
        return "Job with id " +jobId + " has been deleted success.";
    }

    @Override
    public List<JobGetDto> getAllCompanyJob(Optional<Long> companyId) {
       List<Job> jobs;
       if(companyId.isPresent()){
           jobs=jobRepository.findByCompanyId(companyId.get());
       }else
           jobs= jobRepository.findAll();

       return jobs.stream().map(p -> {
           return new JobGetDto(p);}).collect(Collectors.toList());
}

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }
}
