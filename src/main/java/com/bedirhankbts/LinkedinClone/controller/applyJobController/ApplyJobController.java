package com.bedirhankbts.LinkedinClone.controller.applyJobController;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
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
    @GetMapping("/getAllUserApplyJob/{userId}")
    public List<ApplyJobDto> getAllUserApplyJob(@RequestParam Optional<Long> userId){
        return applyJobService.getAllUserApplyJob(userId);
    }
    @DeleteMapping("/{applyJobId}")
    public String deleteApplyJobById(@PathVariable("applyJobId") Long applyJobId){
        return applyJobService.deleteApplyJobById(applyJobId);
    }

}
