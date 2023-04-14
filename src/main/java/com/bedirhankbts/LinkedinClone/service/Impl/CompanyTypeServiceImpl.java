package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.model.CompanyType;
import com.bedirhankbts.LinkedinClone.repository.CompanyTypeRepository;
import com.bedirhankbts.LinkedinClone.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;
    @Override
    public List<CompanyType> getAllCompanyType() {
        return companyTypeRepository.findAll();
    }

    @Override
    public CompanyType createCompanyType(CompanyType newCompanyType) {

        return  companyTypeRepository.save(newCompanyType);
    }

    @Override
    public CompanyType getCompanyTypeById(Long companyTypeId) {
        return  companyTypeRepository.findById(companyTypeId).orElse(null);
    }
}
