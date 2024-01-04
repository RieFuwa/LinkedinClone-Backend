package com.bedirhankbts.LinkedinClone.dto.likeDto;

import com.bedirhankbts.LinkedinClone.model.Like;
import lombok.Data;

@Data
public class LikeDto {
    private Long id ;
    private Long userId;
    private Long postId;

    public  LikeDto(Like entity){
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.postId=entity.getPost().getId();
    }
}
