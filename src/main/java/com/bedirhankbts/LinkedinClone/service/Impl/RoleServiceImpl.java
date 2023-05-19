package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.roleDto.AddRoleToUserDto;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.RoleRepository;
import com.bedirhankbts.LinkedinClone.request.roleRequest.AddRoleByUserCreateRequest;
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

       userService.addRoleToUser(addRoleByUserCreateRequest.getUserId(),addRoleByUserCreateRequest.getRoleId());
         User getUser= userService.getUserById(addRoleByUserCreateRequest.getUserId());
        if(addRoleByUserCreateRequest == null){

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }
        AddRoleToUserDto addRoleToUserDto = new AddRoleToUserDto(getUser.getId(),getUser.getRoles());

        addRoleToUserDto.setUserId(addRoleByUserCreateRequest.getUserId());
        return new ResponseEntity<>(addRoleToUserDto, HttpStatus.CREATED);

    }

    @Override
    public String deleteRoleById(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            return "Role with id not found" +roleId+".";
        }
        roleRepository.deleteById(roleId);
        return "Role with id " +roleId+ " has been deleted success.";
    }
}
