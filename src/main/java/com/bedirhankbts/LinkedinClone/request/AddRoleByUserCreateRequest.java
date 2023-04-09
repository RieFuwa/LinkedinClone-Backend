package com.bedirhankbts.LinkedinClone.request;

import lombok.Data;

@Data
public class AddRoleByUserCreateRequest {
    private Long roleId;
    private Long userId;
}
