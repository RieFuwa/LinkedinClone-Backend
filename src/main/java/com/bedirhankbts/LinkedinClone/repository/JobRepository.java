package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount;
import com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount;
import com.bedirhankbts.LinkedinClone.model.Job;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByCompanyId(Long companyId);
    List<Job> findByJobTypeId(Long jobTypeId);

    Long countByCompanyId(Long companyId);

    @Query(" SELECT distinct new com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount(JT.jobTypeName,J.jobType.id,count(J.jobType.id)) " +
            "FROM Job J " +
            "LEFT JOIN JobType JT ON JT.id = J.jobType.id " +
            "GROUP BY JT.jobTypeName, J.jobType.id "+
            "ORDER BY COUNT(J.jobType.id) DESC ") List<JobJobTypeCount> queryJobTypeTitleCount();

    @Query(" SELECT distinct new com.bedirhankbts.LinkedinClone.dto.queryDto.JobJobTypeCount(JT.jobTypeName,J.jobType.id,count(J.jobType.id)) " +
            "FROM Job J " +
            "LEFT JOIN JobType JT ON JT.id = J.jobType.id " +
            "lEFT JOIN Company C ON J.company.id = C.id " +
            "WHERE C.id = :companyId " +
            "GROUP BY JT.jobTypeName, J.jobType.id "+
            "ORDER BY COUNT(J.jobType.id) DESC ")  List<JobJobTypeCount> queryJobTypeTitleCountCompany(@Param("companyId") Long companyId);

    @Query(" SELECT new com.bedirhankbts.LinkedinClone.dto.queryDto.JobApplyCount(J.id, MAX(AJ.id), JT.jobTypeName, COUNT(J.id)) " +
            "FROM Job J " +
            "LEFT JOIN ApplyJob AJ ON AJ.job.id = J.id " +
            "LEFT JOIN JobType JT ON JT.id = J.jobType.id " +
            "LEFT JOIN Company C ON J.company.id = C.id " +
            "WHERE C.id = :companyId AND AJ.id IS NOT NULL " +
            "GROUP BY J.id, JT.jobTypeName")
    List<JobApplyCount> queryApplyJobCountCompany(@Param("companyId") Long companyId);



}
