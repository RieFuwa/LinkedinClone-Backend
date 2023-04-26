package com.bedirhankbts.LinkedinClone.controller.companyTypeController;

import com.bedirhankbts.LinkedinClone.model.CompanyType;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyType")
public class CompanyTypeController {
    @Autowired
    private CompanyTypeService companyTypeService;

    @PostMapping("/add")
    public ResponseEntity<Void> createCompanyType(@RequestBody CompanyType newCompanyType) {
        CompanyType companyType = companyTypeService.createCompanyType(newCompanyType);
        if(companyType != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAll")
    public List<CompanyType> getAllCompanyType(){
        return companyTypeService.getAllCompanyType();
    }

    @GetMapping("/{companyTypeId}")
    public CompanyType getCompanyTypeById(@PathVariable("companyTypeId")Long companyTypeId){
        return companyTypeService.getCompanyTypeById(companyTypeId);
    }
    @DeleteMapping("/{companyTypeId}")
    public String deleteCompanyTypeById(@PathVariable("companyTypeId") Long companyTypeId){
        return companyTypeService.deleteCompanyTypeById(companyTypeId);
    }

}
