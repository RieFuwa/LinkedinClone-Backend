package com.bedirhankbts.LinkedinClone.request.userRequest;

import lombok.Data;

@Data
public class UserCreateRequest {
    private Long id;
    private String userName;
    private String userMail;
    private String userPassword;
    private Boolean isVerified;
    private String userDetail;

    private String userUniversity;

    private String userAddress;

}
