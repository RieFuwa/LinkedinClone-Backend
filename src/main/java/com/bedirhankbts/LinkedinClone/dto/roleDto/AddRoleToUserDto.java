package com.bedirhankbts.LinkedinClone.dto.roleDto;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.model.User;
import lombok.Data;

import java.util.List;

@Data
public class AddRoleToUserDto {

    private Long userId;
    private List<Role> roleList;
    public AddRoleToUserDto(Long userId, List<Role> roleList){
        this.userId=userId;
        this.roleList=roleList;

    }
}
