package com.bedirhankbts.LinkedinClone.dto.postDto;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.dto.reportDto.ReportDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.Report;
import com.bedirhankbts.LinkedinClone.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostGetDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long connectedPostId;

    private String postTitle;
    private String postText;
    private Date createDate;
    private List<LikeDto> likeList;
    private List<ReportDto> reportList;

    public PostGetDto(Post entity, List<LikeDto> likeList, List<ReportDto> reportList){

        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.userName=entity.getUser().getUserName();

        if(entity.getConnectedPost()!=null){
            this.connectedPostId=entity.getConnectedPost().getId();
        }
        this.postTitle=entity.getPostTitle();
        this.postText=entity.getPostText();
        this.createDate=entity.getCreateDate();
        this.likeList=likeList;
        this.reportList=reportList;


    }
}
