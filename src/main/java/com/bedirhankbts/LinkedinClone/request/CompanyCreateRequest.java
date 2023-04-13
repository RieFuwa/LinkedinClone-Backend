package com.bedirhankbts.LinkedinClone.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyCreateRequest {

    private Long id;
    private Long userId;
    private String companyName;
    private String companyMail;
    private String companyDetails;


}
