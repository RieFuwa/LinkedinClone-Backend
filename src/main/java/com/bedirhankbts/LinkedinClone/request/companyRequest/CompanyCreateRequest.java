package com.bedirhankbts.LinkedinClone.request.companyRequest;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyCreateRequest {

    private Long id;
    private Long userId;
    private Long companyTypeId;
    private String companyName;
    private String companyMail;
    private String companyDetails;



}
