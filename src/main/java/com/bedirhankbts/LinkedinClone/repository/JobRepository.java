package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByCompanyId(Long companyId);
}
