package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.TopFiveJob;
import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.model.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long> {
    List<ApplyJob> findByUserIdAndJobId(Long userId, Long jobId);

    List<ApplyJob> findByUserId(Long userId);

    List<ApplyJob> findByJobId(Long jobId);
    Long countApplyJobByJobCompanyId(Long companyId);
    Long countApplyJobByJobId(Long jobId);

    @Query(value = "SELECT new com.bedirhankbts.LinkedinClone.dto.queryDto.TopFiveJob(J.id, MAX(AJ.id), JT.jobTypeName, J.company.companyName, COUNT(J.id)) " +
            "FROM Job J " +
            "LEFT JOIN ApplyJob AJ ON AJ.job.id = J.id " +
            "LEFT JOIN JobType JT ON JT.id = J.jobType.id " +
            "WHERE AJ.id IS NOT NULL " +
            "GROUP BY J.id, JT.jobTypeName, J.company.companyName " +
            "ORDER BY COUNT(J.id) DESC")
    List<TopFiveJob> queryTop5JobsByApplyCount(Pageable pageable);

}
