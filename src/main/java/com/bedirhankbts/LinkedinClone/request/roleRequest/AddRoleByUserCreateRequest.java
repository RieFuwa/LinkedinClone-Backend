package com.bedirhankbts.LinkedinClone.request.roleRequest;

import lombok.Data;

@Data
public class AddRoleByUserCreateRequest {
    private Long roleId;
    private Long userId;
}
