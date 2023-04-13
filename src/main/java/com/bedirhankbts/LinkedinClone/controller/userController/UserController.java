package com.bedirhankbts.LinkedinClone.controller.userController;

import com.bedirhankbts.LinkedinClone.dto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.request.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserCreateDto> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }
    //@Cacheable(value = "User")
    @GetMapping("/getAll")
    @Cacheable(value = "User")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    @Cacheable(key = "#userId",value = "User")
    public User getUserById(@PathVariable("userId")Long userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUserById(@PathVariable("userId") Long userId){
        return userService.deleteUserById(userId);
    }
}
