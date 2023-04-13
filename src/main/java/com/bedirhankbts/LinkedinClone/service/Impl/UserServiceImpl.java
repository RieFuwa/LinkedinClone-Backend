package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.RoleRepository;
import com.bedirhankbts.LinkedinClone.repository.UserRepository;
import com.bedirhankbts.LinkedinClone.request.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<UserCreateDto> createUser(UserCreateRequest newUser) {
        UserCreateDto userCreateDto = new UserCreateDto();
        User toCreate= new User();
        if(userRepository.findByUserMail(newUser.getUserMail()) != null) {
            userCreateDto.setMessage("Mail already in use.");
            return new ResponseEntity<>(userCreateDto, HttpStatus.BAD_REQUEST);
        }
        toCreate.setId(newUser.getId());
        toCreate.setUserName(newUser.getUserName());
        toCreate.setUserMail(newUser.getUserMail());
        toCreate.setUserPassword(newUser.getUserPassword());
        toCreate.setUserDetails(newUser.getUserDetails());
        toCreate.setRoles(new ArrayList<>());
        toCreate.setCreateDate(new Date());
        toCreate.setUpdateDate(new Date());
        toCreate.setIsVerified(newUser.getIsVerified());
        userRepository.save(toCreate);
        userCreateDto.setMessage("User successfully created.");
        userCreateDto.setUserId(toCreate.getId());
        return new ResponseEntity<>(userCreateDto, HttpStatus.CREATED);
    }
//EXCEPTION CLASS.
    @Override
    public Object addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("Could not found with id"));
        Role role = roleRepository.findById(roleId).orElseThrow(()->new RuntimeException("Could not found with id"));;
        if(role== null && user ==null){
            return null;
        }
        return user.getRoles().add(role);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            return "User with id not found" +userId+".";
        }
        userRepository.deleteById(userId);
        return "User with id " +userId+ " has been deleted success.";
    }

}


