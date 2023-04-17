package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyCreateDto;
import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyUpdateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyCreateRequest;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    ResponseEntity<CompanyCreateDto> createCompany(CompanyCreateRequest companyCreateRequest);

    List<Company> getAllCompany();

    Company getCompanyById(Long companyId);

    String deleteCompanyById(Long companyId);

    ResponseEntity<CompanyUpdateDto> companyUpdateByCompanyId(Long companyId, CompanyUpdateRequest updateCompany);
}
