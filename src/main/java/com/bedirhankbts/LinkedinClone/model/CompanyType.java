package com.bedirhankbts.LinkedinClone.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "companyType")
public class CompanyType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String companyTypeName;

}
