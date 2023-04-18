package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.Report;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.ReportRepository;
import com.bedirhankbts.LinkedinClone.request.reportRequest.ReportCreateRequest;
import com.bedirhankbts.LinkedinClone.service.LikeService;
import com.bedirhankbts.LinkedinClone.service.PostService;
import com.bedirhankbts.LinkedinClone.service.ReportService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Override
    public Report createReport(ReportCreateRequest newReport) {
        User user = userService.getUserById(newReport.getUserId());
        Post post = postService.getPostById(newReport.getPostId());
        if(user == null && post == null)
            return null;
        Report toCreate= new Report();
        toCreate.setId(newReport.getId());
        toCreate.setCreateDate(new Date());
        toCreate.setUser(user);
        toCreate.setPost(post);
        toCreate.setReportText(newReport.getReportText());
        return reportRepository.save(toCreate);
    }

    @Override
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    @Override
    public String deleteReportById(Long reportId) {
        if (!reportRepository.existsById(reportId)) {
            return "Report with id not found" +reportId+".";
        }
        reportRepository.deleteById(reportId);
        return "Report with id " +reportId+ " has been deleted success.";
    }

    @Override
    public List<ReportDto> getAllPostReports(Optional<Long> postId) {
        List<Report> reports;
        if(postId.isPresent()){
            reports=reportRepository.findByPostId(postId.get());
        }else
            reports = reportRepository.findAll();
        return reports.stream().map(p -> {
            return new ReportDto(p);}).collect(Collectors.toList());
    }
}
