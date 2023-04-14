package com.bedirhankbts.LinkedinClone.repository;


import com.bedirhankbts.LinkedinClone.model.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType,Long> {
}
