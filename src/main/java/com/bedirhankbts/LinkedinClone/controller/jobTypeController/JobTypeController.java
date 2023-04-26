package com.bedirhankbts.LinkedinClone.controller.jobTypeController;

import com.bedirhankbts.LinkedinClone.model.CompanyType;
import com.bedirhankbts.LinkedinClone.model.JobType;
import com.bedirhankbts.LinkedinClone.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//düz cons yazmak zaman kazandırır.
@RestController
@RequestMapping("/jobType")
public class JobTypeController {
    @Autowired
    private JobTypeService jobTypeService;

    @PostMapping("/add")
    public ResponseEntity<Void> createJobType(@RequestBody JobType newJobType) {
        JobType jobType = jobTypeService.createJobType(newJobType);
        if(jobType != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAll")
    public List<JobType> getAllJobType(){
        return jobTypeService.getAllJobType();
    }

    @GetMapping("/{jobTypeId}")
    public JobType getJobTypeById(@PathVariable("jobTypeId")Long jobTypeId){
        return jobTypeService.getJobTypeById(jobTypeId);
    }

    @DeleteMapping("/{jobTypeId}")
    public String deleteJobTypeById(@PathVariable("jobTypeId") Long jobTypeId){
        return jobTypeService.deleteCompanyTypeById(jobTypeId);
    }

}
