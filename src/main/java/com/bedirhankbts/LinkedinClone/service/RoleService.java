package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.roleDto.AddRoleToUserDto;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.request.roleRequest.AddRoleByUserCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    ResponseEntity<Void> addRole(Role newRole);

    List<Role> getAllRole();

    ResponseEntity<AddRoleToUserDto> addRoleToUser(AddRoleByUserCreateRequest addRoleByUserCreateRequest);

    String deleteRoleById(Long roleId);
}
