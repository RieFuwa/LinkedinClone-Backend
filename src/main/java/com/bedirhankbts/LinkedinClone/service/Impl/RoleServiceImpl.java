package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.AddRoleToUserDto;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.repository.RoleRepository;
import com.bedirhankbts.LinkedinClone.request.AddRoleByUserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.RoleService;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> addRole(Role newRole) {
        if(newRole != null){
            roleRepository.save(newRole);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public ResponseEntity<AddRoleToUserDto> addRoleToUser(AddRoleByUserCreateRequest addRoleByUserCreateRequest) {
        AddRoleToUserDto addRoleToUserDto = new AddRoleToUserDto();
        userService.addRoleToUser(addRoleByUserCreateRequest.getUserId(),addRoleByUserCreateRequest.getRoleId());
        if(addRoleByUserCreateRequest == null){
            addRoleToUserDto.setMessage("Role not created.");
            return new ResponseEntity<>(addRoleToUserDto, HttpStatus.BAD_REQUEST);

        }
        addRoleToUserDto.setMessage("Role created for userId: "+addRoleByUserCreateRequest.getUserId());
        addRoleToUserDto.setUserId(addRoleByUserCreateRequest.getUserId());
        return new ResponseEntity<>(addRoleToUserDto, HttpStatus.CREATED);

    }
}
