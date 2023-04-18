package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findByPostId(Long postId);

}
