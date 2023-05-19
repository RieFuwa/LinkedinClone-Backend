package com.bedirhankbts.LinkedinClone.dto.userDto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String message;
    private Long userId;
    private String userName;
    private String userMail;

}
