package com.bedirhankbts.LinkedinClone.request.userRequest;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userMail;
    private String userPassword;
}
