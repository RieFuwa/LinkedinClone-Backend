package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.request.UserCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<UserCreateDto> createUser(UserCreateRequest userCreateRequest);

    List<User> getAllUser();

    String deleteUserById(Long userId);

    Object addRoleToUser(Long userId, Long roleId);

    User getUserById(Long userId);
}
