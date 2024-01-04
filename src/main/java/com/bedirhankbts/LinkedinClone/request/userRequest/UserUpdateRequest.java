package com.bedirhankbts.LinkedinClone.request.userRequest;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String userName;
    private String userMail;
    private String userPassword;
    private String userDetail;
    private String userUniversity;
    private String userAddress;
}
