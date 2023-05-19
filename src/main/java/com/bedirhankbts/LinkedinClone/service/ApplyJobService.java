package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.applyJobDto.ApplyJobDto;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.TopFiveJob;
import com.bedirhankbts.LinkedinClone.request.applyJobRequest.ApplyJobCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApplyJobService {
    ResponseEntity<ApplyJobDto> toApplyJob(ApplyJobCreateRequest newApplyJob);

    List<ApplyJobDto> getAllApplyJob(Optional<Long> userId, Optional<Long> jobId);

    String deleteApplyJobById(Long applyJobId);

    List<ApplyJobDto> getAllUserApplyJob(Optional<Long> userId);

    List<ApplyJobDto> getAllJobApplyJob(Optional<Long> jobId);

    Long getApplyJobCountByCompanyId(Optional<Long> companyId);

    Long getApplyJobCountByJobId(Optional<Long> jobId);

    List<TopFiveJob> getQueryTop5JobsByApplyCount();
}
