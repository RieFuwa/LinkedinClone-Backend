package com.bedirhankbts.LinkedinClone.service;

import com.bedirhankbts.LinkedinClone.dto.userDto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserUpdateDto;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserUpdateRequest;
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

    ResponseEntity<UserUpdateDto> userUpdateByUserId(Long userId, UserUpdateRequest updateUser);
}
