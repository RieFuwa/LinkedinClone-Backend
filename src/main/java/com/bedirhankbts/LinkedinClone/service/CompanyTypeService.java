package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.model.CompanyType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyTypeService {
    List<CompanyType> getAllCompanyType();

    CompanyType createCompanyType(CompanyType newCompanyType);

    CompanyType getCompanyTypeById(Long companyTypeId);
}
