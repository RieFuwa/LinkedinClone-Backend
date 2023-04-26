package com.bedirhankbts.LinkedinClone.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jobType")
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String jobTypeName;
}
