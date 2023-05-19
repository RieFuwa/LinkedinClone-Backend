package com.bedirhankbts.LinkedinClone.controller.companyController;

import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyCreateDto;
import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyGetDto;
import com.bedirhankbts.LinkedinClone.dto.companyDto.CompanyUpdateDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserUpdateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyCreateRequest;
import com.bedirhankbts.LinkedinClone.request.companyRequest.CompanyUpdateRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserUpdateRequest;
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
    @PutMapping("/companyUpdate/{companyId}")
    public ResponseEntity<CompanyUpdateDto> companyUpdateByCompanyId(@PathVariable("companyId") Long companyId, @RequestBody CompanyUpdateRequest updateCompany){
        return companyService.companyUpdateByCompanyId(companyId,updateCompany);
    }
    @GetMapping("/getAll")
    @Cacheable(value = "Company")
    public List<CompanyGetDto> getAllCompany(){
        return companyService.getAllCompany().stream().map(key-> new CompanyGetDto(key)).toList();
    }
    @GetMapping("/getAllCompanyByCount")
    public Long getAllCompanyByCount(){
        return companyService.getAllCompanyByCount();
    }
    @GetMapping("/{companyId}")
    public CompanyGetDto getCompanyById(@PathVariable("companyId")Long companyId){
        Company company = companyService.getCompanyById(companyId);
        return new CompanyGetDto(company);
    }
    @DeleteMapping("/{companyId}")
    public String deleteCompanyById(@PathVariable("companyId") Long companyId){
        return companyService.deleteCompanyById(companyId);
    }
}
