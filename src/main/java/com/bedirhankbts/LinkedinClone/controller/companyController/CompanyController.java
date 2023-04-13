package com.bedirhankbts.LinkedinClone.controller.companyController;

import com.bedirhankbts.LinkedinClone.dto.CompanyCreateDto;
import com.bedirhankbts.LinkedinClone.dto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.request.CompanyCreateRequest;
import com.bedirhankbts.LinkedinClone.request.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<CompanyCreateDto> createCompany(@RequestBody CompanyCreateRequest companyCreateRequest) {
        return companyService.createCompany(companyCreateRequest);
    }
    @GetMapping("/getAll")
    @Cacheable(value = "Company")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable("companyId")Long companyId){
        return companyService.getCompanyById(companyId);
    }
    @DeleteMapping("/{companyId}")
    public String deleteCompanyById(@PathVariable("companyId") Long companyId){
        return companyService.deleteCompanyById(companyId);
    }
}
