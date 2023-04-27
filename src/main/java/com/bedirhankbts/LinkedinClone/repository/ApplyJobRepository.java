package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.ApplyJob;
import com.bedirhankbts.LinkedinClone.model.Job;
import com.bedirhankbts.LinkedinClone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long> {
    List<ApplyJob> findByUserIdAndJobId(Long userId, Long jobId);

    List<ApplyJob> findByUserId(Long userId);

    List<ApplyJob> findByJobId(Long jobId);
}
