package com.bedirhankbts.LinkedinClone.controller.jobController;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobUpdateDto;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserUpdateDto;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobUpdateRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserUpdateRequest;
import com.bedirhankbts.LinkedinClone.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<JobCreateDto> createJob(@RequestBody JobCreateRequest jobCreateRequest) {
        return jobService.createJob(jobCreateRequest);
    }

    @GetMapping("/getAll")
    @Cacheable(value = "Company")
    public List<JobGetDto> getAllJob(){
        return jobService.getAllJob();
    }

    @GetMapping("/{jobId}")
    public JobGetDto getJobById(@PathVariable("jobId")Long jobId){
        return jobService.getOneJobWithParameters(jobId);

    }
    @GetMapping("/getJobTypeByCount")
    public List<JobJobTypeCount> getJobTypeByCount(){
        return jobService.getJobTypeByCount();
    }

    @GetMapping("/countByJobByCompany/{companyId}")
    public Long countByJobByCompany(@PathVariable Long companyId){
        return jobService.countByJobByCompany(companyId);
    }

    @GetMapping("/getJobTypeByCountCompany/{companyId}")
    public List<JobJobTypeCount> getJobTypeByCountCompany(@PathVariable("companyId") Long companyId){
        return jobService.getJobTypeByCountCompany(companyId);
    }
    @GetMapping("/getJobApplyByCountCompany/{companyId}")
    public List<JobApplyCount> getJobApplyByCountCompany(@PathVariable("companyId") Long companyId){
        return jobService.getJobApplyByCountCompany(companyId);
    }


    @PutMapping("/jobUpdate/{jobId}")
    public ResponseEntity<JobUpdateDto> jobUpdateByJobId(@PathVariable("jobId") Long jobId, @RequestBody JobUpdateRequest updateJob){
        return jobService.jobUpdateByJobId(jobId,updateJob);
    }

    @DeleteMapping("/{jobId}")
    public String deleteJobById(@PathVariable("jobId") Long jobId){
        return jobService.deleteJobById(jobId);
    }

    @GetMapping("/getAllCompanyJob{companyId}")
    public List<JobGetDto> getAllCompanyJob(@RequestParam Optional<Long> companyId){
        return jobService.getAllCompanyJob(companyId);
    }

    @GetMapping("/jobType{jobTypeId}")
    public List<JobGetDto> getJobByJobTypeId(@RequestParam Optional<Long> jobTypeId){
        return jobService.getJobByJobTypeId(jobTypeId);
    }

}
