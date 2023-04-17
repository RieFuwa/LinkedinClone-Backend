package com.bedirhankbts.LinkedinClone.dto.postDto;

import com.bedirhankbts.LinkedinClone.model.Post;
import com.bedirhankbts.LinkedinClone.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class PostGetDto {
    private Long id;
    private User user;
    private Long connectedPostId;
    private String postTitle;
    private String postText;
    private Date createDate;


    public PostGetDto(Post entity){

        this.id=entity.getId();
        this.user=entity.getUser();
        if(entity.getConnectedPost()!=null){
            this.connectedPostId=entity.getConnectedPost().getId();
        }
        this.postTitle=entity.getPostTitle();
        this.postText=entity.getPostText();
        this.createDate=entity.getCreateDate();


    }
}
