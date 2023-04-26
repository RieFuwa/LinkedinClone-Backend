package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends JpaRepository<JobType,Long> {
}
