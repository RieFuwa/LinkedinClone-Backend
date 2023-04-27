package com.bedirhankbts.LinkedinClone.controller.jobController;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobCreateDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.request.jobRequest.JobCreateRequest;
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

    @DeleteMapping("/{jobId}")
    public String deleteJobById(@PathVariable("jobId") Long jobId){
        return jobService.deleteJobById(jobId);
    }

    @GetMapping("/getAllCompanyJob/{companyId}")
    public List<JobGetDto> getAllCompanyJob(@RequestParam Optional<Long> companyId){
        return jobService.getAllCompanyJob(companyId);
    }

    @GetMapping("/jobType/{jobTypeId}")
    public List<JobGetDto> getJobByJobTypeId(@RequestParam Optional<Long> jobTypeId){
        return jobService.getJobByJobTypeId(jobTypeId);
    }

}
