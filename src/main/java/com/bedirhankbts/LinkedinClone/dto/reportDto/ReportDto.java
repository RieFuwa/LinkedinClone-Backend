package com.bedirhankbts.LinkedinClone.dto.reportDto;

import com.bedirhankbts.LinkedinClone.model.Report;
import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {
    private Long id ;
    private Long userId;

    private String reportText;
    private Date createDate;

    public  ReportDto(Report entity){
        this.id= entity.getId();
        this.reportText=entity.getReportText();
        this.userId=entity.getUser().getId();
        this.createDate=entity.getCreateDate();
    }
}
