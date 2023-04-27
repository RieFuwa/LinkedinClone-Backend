package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.jobDto.JobGetDto;
import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.ApplyJobRepository;
import com.bedirhankbts.LinkedinClone.request.applyJobRequest.ApplyJobCreateRequest;
import com.bedirhankbts.LinkedinClone.service.ApplyJobService;
import com.bedirhankbts.LinkedinClone.service.JobService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplyJobServiceImpl implements ApplyJobService {
    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;


    @Override
    public ResponseEntity<ApplyJobDto> toApplyJob(ApplyJobCreateRequest newApplyJob) {
        User user = userService.getUserById(newApplyJob.getUserId());
        Job job = jobService.getJobById(newApplyJob.getJobId());
        if(user !=null && job!=null){
            ApplyJob toCreate = new ApplyJob();
            toCreate.setId(newApplyJob.getId());
            toCreate.setUser(user);
            toCreate.setJob(job);
            applyJobRepository.save(toCreate);
            ApplyJobDto applyJobCreateDto = new ApplyJobDto(toCreate);
            return  new ResponseEntity<>(applyJobCreateDto,HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<ApplyJobDto> getAllApplyJob(Optional<Long> userId, Optional<Long> jobId) {
        List<ApplyJob> job;
        if(userId.isPresent() && jobId.isPresent()){
            job = applyJobRepository.findByUserIdAndJobId(userId.get(), jobId.get());
        } else if (userId.isPresent()) {
            job = applyJobRepository.findByUserId(userId.get());
        } else if (jobId.isPresent()) {
            job = applyJobRepository.findByJobId(jobId.get());
        }else
            job = applyJobRepository.findAll();
        return job.stream().map(jobs-> new ApplyJobDto(jobs)).collect(Collectors.toList());
    }

    @Override
    public String deleteApplyJobById(Long applyJobId) {
        if (!applyJobRepository.existsById(applyJobId)) {
            return "Like with id not found " +applyJobId+".";
        }
        applyJobRepository.deleteById(applyJobId);
        return "Like with id " +applyJobId+ " has been deleted success.";
    }

    @Override
    public List<ApplyJobDto> getAllUserApplyJob(Optional<Long> userId) {
        List<ApplyJob> applyJobs;
        if(userId.isPresent()){
            applyJobs=applyJobRepository.findByUserId(userId.get());
        }else
            applyJobs= applyJobRepository.findAll();

        return applyJobs.stream().map(p -> {
            return new ApplyJobDto(p);}).collect(Collectors.toList());
    }
}
