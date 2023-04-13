package com.bedirhankbts.LinkedinClone.controller.roleController;

import com.bedirhankbts.LinkedinClone.dto.AddRoleToUserDto;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.request.AddRoleByUserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Void> addRole(@RequestBody Role newRole) {
        return roleService.addRole(newRole);
    }
    @GetMapping("/getAll")
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<AddRoleToUserDto> addRoleToUser(@RequestBody AddRoleByUserCreateRequest AddRoleByUserCreateRequest) {
        return roleService.addRoleToUser(AddRoleByUserCreateRequest);

    }

}
