package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.CompanyCreateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.request.CompanyCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    ResponseEntity<CompanyCreateDto> createCompany(CompanyCreateRequest companyCreateRequest);

    List<Company> getAllCompany();

    Company getCompanyById(Long companyId);

    String deleteCompanyById(Long companyId);
}
