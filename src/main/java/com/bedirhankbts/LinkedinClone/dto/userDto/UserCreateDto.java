package com.bedirhankbts.LinkedinClone.dto.userDto;

import com.bedirhankbts.LinkedinClone.dto.likeDto.LikeDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserCreateDto {
    private String message;
    private Long userId;
    private Long companyId;
    private String userName;
    private String userMail;
    private List<Role> roleList;
}
