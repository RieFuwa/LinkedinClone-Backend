package com.bedirhankbts.LinkedinClone.service.Impl;
import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobUpdateDto;
import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.dto.postDto.PostGetDto;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount;
import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.bedirhankbts.LinkedinClone.model.*;
import com.bedirhankbts.LinkedinClone.repository.JobRepository;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobUpdateRequest;
import com.bedirhankbts.LinkedinClone.service.ApplyJobService;
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
    private ApplyJobService applyJobService;

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
    public List<JobGetDto> getAllJob() {
        List<Job> job;

        job = jobRepository.findAll();
        return job.stream().map(p -> {
            List<ApplyJobDto> applyJobDtoList = applyJobService.getAllApplyJob(Optional.ofNullable(null),Optional.of(p.getId()));

            return new JobGetDto(p, applyJobDtoList);}).collect(Collectors.toList());
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
           List<ApplyJobDto> applyJobDtoList = applyJobService.getAllApplyJob(Optional.ofNullable(null), Optional.of(p.getId()));

           return new JobGetDto(p,applyJobDtoList);}).collect(Collectors.toList());

}

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    @Override
    public JobGetDto getOneJobWithParameters(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        List<ApplyJobDto> applyJobDtoList = applyJobService.getAllApplyJob(Optional.ofNullable(null), Optional.of(jobId));
        return new JobGetDto(job,applyJobDtoList);
    }

    @Override
    public List<JobGetDto> getJobByJobTypeId(Optional<Long> jobTypeId) {
        List<Job> job;
        if(jobTypeId.isPresent()){
            job=jobRepository.findByJobTypeId(jobTypeId.get());
        }else
            job = jobRepository.findAll();
        return job.stream().map(p -> {
            List<ApplyJobDto> applyJobDtoList = applyJobService.getAllApplyJob(Optional.ofNullable(null), Optional.of(p.getId()));

            return new JobGetDto(p, applyJobDtoList);}).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<JobUpdateDto> jobUpdateByJobId(Long jobId, JobUpdateRequest updateJob) {
        JobUpdateDto jobUpdateDto = new JobUpdateDto();
        Optional<Job> job = jobRepository.findById(jobId);
        Job toUpdate = job.get();
        toUpdate.setJobDetails(updateJob.getJobDetails());
        jobUpdateDto.setMessage("Job successfully updated.");
        jobUpdateDto.setJobId(toUpdate.getId());
        jobRepository.save(toUpdate);
        return  new ResponseEntity<>(jobUpdateDto,HttpStatus.CREATED);
    }

    @Override
    public List<JobJobTypeCount> getJobTypeByCount() {
        List<JobJobTypeCount> jobDto = jobRepository.queryJobTypeTitleCount();
        return jobDto;
    }

    @Override
    public List<JobJobTypeCount> getJobTypeByCountCompany(Long companyId) {
        return jobRepository.queryJobTypeTitleCountCompany(companyId);
    }

    @Override
    public List<JobApplyCount> getJobApplyByCountCompany(Long companyId) {
        return jobRepository.queryApplyJobCountCompany(companyId);

    }

    @Override
    public Long countByJobByCompany(Long companyId) {
       return jobRepository.countByCompanyId(companyId);
    }


}
