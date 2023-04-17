package com.bedirhankbts.LinkedinClone.dto.companyDto;

import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.CompanyType;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyGetDto {
    private Long id;
    private Long userId;
    private CompanyType companyType;
    private String companyName;
    private String companyMail;
    private String companyDetails;
    private String companyAddress;
    private Date createDate;
    private Date updateDate;
    public CompanyGetDto(Company entity){
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.companyType=entity.getCompanyType();
        this.companyAddress=entity.getCompanyAddress();
        this.companyMail=entity.getCompanyMail();
        this.companyDetails=entity.getCompanyDetails();
        this.companyName= entity.getCompanyName();
        this.createDate=entity.getCreateDate();
        this.updateDate=entity.getUpdateDate();
    }
}
