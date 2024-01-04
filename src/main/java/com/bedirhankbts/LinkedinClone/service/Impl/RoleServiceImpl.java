package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.repository.RoleRepository;
import com.bedirhankbts.LinkedinClone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

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
}
