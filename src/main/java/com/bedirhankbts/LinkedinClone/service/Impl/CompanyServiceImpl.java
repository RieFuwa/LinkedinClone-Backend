package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyCreateDto;
import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyUpdateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.CompanyType;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.CompanyRepository;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyCreateRequest;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyUpdateRequest;
import com.bedirhankbts.LinkedinClone.service.CompanyService;
import com.bedirhankbts.LinkedinClone.service.CompanyTypeService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private CompanyTypeService companyTypeService;
    @Override
    public ResponseEntity<CompanyCreateDto> createCompany(CompanyCreateRequest companyCreateRequest) {
        CompanyCreateDto companyCreateDto = new CompanyCreateDto();
        User user = userService.getUserById(companyCreateRequest.getUserId());
        CompanyType companyType = companyTypeService.getCompanyTypeById(companyCreateRequest.getCompanyTypeId());
        if(user==null && companyType==null) {
            companyCreateDto.setMessage("Company not created. UserId not found.");
            return new ResponseEntity<>(companyCreateDto, HttpStatus.BAD_REQUEST);
        }
        Company toCreate = new Company();
        toCreate.setId(companyCreateRequest.getId());
        toCreate.setUser(user);
        toCreate.setCompanyType(companyType);
        toCreate.setCompanyAddress(companyCreateRequest.getCompanyAddress());
        toCreate.setCompanyMail(companyCreateRequest.getCompanyMail());
        toCreate.setCompanyName(companyCreateRequest.getCompanyName());
        toCreate.setCompanyDetails(companyCreateRequest.getCompanyDetails());
        toCreate.setCreateDate(new Date());
        toCreate.setUpdateDate(new Date());
        companyRepository.save(toCreate);
        companyCreateDto.setMessage("Company successfully created.");
        companyCreateDto.setCompanyId(toCreate.getId());
        companyCreateDto.setUserId(toCreate.getUser().getId());
        return new ResponseEntity<>(companyCreateDto, HttpStatus.CREATED);

    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public String deleteCompanyById(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            return "Company with id not found " +companyId+".";
        }
        companyRepository.deleteById(companyId);
        return "Company with id " +companyId + " has been deleted success.";
    }

    @Override
    public ResponseEntity<CompanyUpdateDto> companyUpdateByCompanyId(Long companyId, CompanyUpdateRequest updateCompany) {
        CompanyUpdateDto companyUpdateDto = new CompanyUpdateDto();
        Optional<Company> company = companyRepository.findById(companyId);
        Company toUpdate = company.get();
        toUpdate.setCompanyName(updateCompany.getCompanyName());
        toUpdate.setCompanyAddress(updateCompany.getCompanyAddress());
        toUpdate.setCompanyDetails(updateCompany.getCompanyDetails());
        toUpdate.setCompanyMail(updateCompany.getCompanyMail());
        toUpdate.setUpdateDate(new Date());
        companyUpdateDto.setMessage("Company successfully updated.");
        companyUpdateDto.setCompanyId(toUpdate.getId());
        companyRepository.save(toUpdate);
        return  new ResponseEntity<>(companyUpdateDto,HttpStatus.CREATED);

    }
}
