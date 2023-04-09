package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    ResponseEntity<Void> addRole(Role newRole);

    List<Role> getAllRole();
}
