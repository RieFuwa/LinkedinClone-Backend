package com.bedirhankbts.LinkedinClone.service;


import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.Report;
import com.bedirhankbts.LinkedinClone.request.reportRequest.ReportCreateRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface ReportService {
    Report createReport(ReportCreateRequest newReport);

    List<Report> getAllReport();

    List<ReportDto> getAllPostReports(Optional<Long> postId);

    Report getReportById(Long reportId);

    String deleteReportById(Long reportId);
}
