package com.bedirhankbts.LinkedinClone.repository;

import com.bedirhankbts.LinkedinClone.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByUserId(Long userId);
}
