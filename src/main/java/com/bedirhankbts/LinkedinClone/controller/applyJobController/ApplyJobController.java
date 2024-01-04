package com.bedirhankbts.LinkedinClone.controller.applyJobController;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.TopFiveJob;
import com.bedirhankbts.LinkedinClone.request.applyJobRequest.ApplyJobCreateRequest;
import com.bedirhankbts.LinkedinClone.service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/toApplyJob")
public class ApplyJobController {
    @Autowired
    private ApplyJobService applyJobService;

    @PostMapping("/add")
    public ResponseEntity<ApplyJobDto> toApplyJob(@RequestBody ApplyJobCreateRequest newApplyJob) {
        return applyJobService.toApplyJob(newApplyJob);
    }
    @GetMapping("/getAll")
    public List<ApplyJobDto> getAllApplyJob(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> jobId){
        return applyJobService.getAllApplyJob(userId,jobId);

    }
    @GetMapping("/getApplyJobCountByCompanyId/{companyId}")
    public Long getApplyJobCountByCompanyId(@PathVariable Optional<Long> companyId){
        return applyJobService.getApplyJobCountByCompanyId(companyId);
    }
    @GetMapping("/getApplyJobCountByJobId/{jobId}")
    public Long getApplyJobCountByJobId(@PathVariable Optional<Long> jobId){
        return applyJobService.getApplyJobCountByJobId(jobId);
    }
    @GetMapping("/getQueryTop5JobsByApplyCount")
    public List<TopFiveJob> getQueryTop5JobsByApplyCount(){
        return applyJobService.getQueryTop5JobsByApplyCount();
    }
    @GetMapping("/getAllUserApplyJob{userId}")
    public List<ApplyJobDto> getAllUserApplyJob(@RequestParam Optional<Long> userId){
        return applyJobService.getAllUserApplyJob(userId);
    }
    @GetMapping("/getAllJobApplyJob{jobId}")
    public List<ApplyJobDto> getAllJobApplyJob(@RequestParam Optional<Long> jobId){
        return applyJobService.getAllJobApplyJob(jobId);
    }
    @DeleteMapping("/{applyJobId}")
    public String deleteApplyJobById(@PathVariable("applyJobId") Long applyJobId){
        return applyJobService.deleteApplyJobById(applyJobId);
    }

}
