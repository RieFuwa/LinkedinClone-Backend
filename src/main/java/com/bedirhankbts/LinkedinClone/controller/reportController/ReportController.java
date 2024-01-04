package com.bedirhankbts.LinkedinClone.controller.reportController;

import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.Report;
import com.bedirhankbts.LinkedinClone.request.reportRequest.ReportCreateRequest;
import com.bedirhankbts.LinkedinClone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    public ResponseEntity<Void> createReport(@RequestBody ReportCreateRequest newReport) {
        Report report = reportService.createReport(newReport);
        if(report != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/getAll")
    public List<ReportDto> getAllReport(){
        return reportService.getAllReport().stream().map(u -> new ReportDto(u)).toList();
    }
    @GetMapping("/getAllPostReports{postId}")
    public List<ReportDto> getAllPostReports(@RequestParam Optional<Long> postId){
        return reportService.getAllPostReports(postId);
    }
    @GetMapping("/{reportId}")
    public ReportDto getReportById(@PathVariable("reportId")Long reportId){
        Report report = reportService.getReportById(reportId);
        return new ReportDto(report);
    }
    @DeleteMapping("/{reportId}") //USER ID SINE GORE SILME
    public String deleteReportById(@PathVariable("reportId") Long reportId){
        return reportService.deleteReportById(reportId);
    }
}