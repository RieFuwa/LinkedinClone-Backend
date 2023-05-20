package com.bedirhankbts.LinkedinClone.service.Impl;

import com.bedirhankbts.LinkedinClone.dto.postDto.PostCreateDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserCreateDto;
import com.bedirhankbts.LinkedinClone.dto.userDto.UserUpdateDto;
import com.bedirhankbts.LinkedinClone.model.Company;
import com.bedirhankbts.LinkedinClone.model.Role;
import com.bedirhankbts.LinkedinClone.model.User;
import com.bedirhankbts.LinkedinClone.repository.CompanyRepository;
import com.bedirhankbts.LinkedinClone.repository.RoleRepository;
import com.bedirhankbts.LinkedinClone.repository.UserRepository;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserCreateRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserLoginRequest;
import com.bedirhankbts.LinkedinClone.request.userRequest.UserUpdateRequest;
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
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
//EXCEPTION CLASS YAZILACAK

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ResponseEntity<UserCreateDto> createUser(UserCreateRequest newUser) {

        UserCreateDto userCreateDto = new UserCreateDto();
        User toCreate= new User();
        if(userRepository.findByUserMail(newUser.getUserMail()) != null) {
            userCreateDto.setMessage("Girdiğiniz E-mail adresi kullanılmaktadır.");
            return new ResponseEntity<>(userCreateDto, HttpStatus.CONFLICT);
        }
        toCreate.setId(newUser.getId());
        toCreate.setUserName(newUser.getUserName());
        toCreate.setUserMail(newUser.getUserMail());
        toCreate.setUserPassword(newUser.getUserPassword());
        toCreate.setUserUniversity(newUser.getUserUniversity());
        toCreate.setUserAddress(newUser.getUserAddress());
        toCreate.setUserDetail(newUser.getUserDetail());
        toCreate.setRoles(new ArrayList<>());
        toCreate.setCreateDate(new Date());
        toCreate.setUpdateDate(new Date());
        toCreate.setIsVerified(newUser.getIsVerified());
        userRepository.save(toCreate);
        userCreateDto.setMessage("User successfully created.");
        userCreateDto.setUserMail(toCreate.getUserMail());
        userCreateDto.setUserId(toCreate.getId());
        userCreateDto.setUserName(toCreate.getUserName());
        return new ResponseEntity<>(userCreateDto, HttpStatus.CREATED);
    }
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
    public User getUserById(Long userId) {
      return userRepository.findById(userId).orElse( null);
    }

    @Override
    public ResponseEntity<UserUpdateDto> userUpdateByUserId(Long userId, UserUpdateRequest updateUser) {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        Optional<User> user = userRepository.findById(userId);
        User toUpdate = user.get();
        toUpdate.setUserName(updateUser.getUserName());
        toUpdate.setUserAddress(updateUser.getUserAddress());
        toUpdate.setUserUniversity(updateUser.getUserUniversity());
        toUpdate.setUserDetail(updateUser.getUserDetail());
        toUpdate.setUserMail(updateUser.getUserMail());
        toUpdate.setUpdateDate(new Date());
        userUpdateDto.setMessage("User successfully updated.");
        userUpdateDto.setUserId(toUpdate.getId());
        userRepository.save(toUpdate);
        return  new ResponseEntity<>(userUpdateDto,HttpStatus.CREATED);


    }

    @Override
    public Long getAllUserByCount() {
        return (long) userRepository.findAll().size();
    }

    public ResponseEntity<UserCreateDto> loginUser(UserLoginRequest userLoginRequest) {
        UserCreateDto userCreateDto = new UserCreateDto();
        User loginToUser = userRepository.findByUserMailAndUserPassword(userLoginRequest.getUserMail(), userLoginRequest.getUserPassword());
        if (loginToUser == null) {
            userCreateDto.setMessage("E-mail adresiniz veya parolanız hatalıdır. Lütfen tekrar deneyiniz.");
            return new ResponseEntity<>(userCreateDto, HttpStatus.UNAUTHORIZED);

        }
        User InLogin = userRepository.findByUserMail(userLoginRequest.getUserMail());
        Company userToCompanyId = companyRepository.findByUserId(InLogin.getId());
        if(userToCompanyId==null){
            userCreateDto.setMessage("User successfully login");
            userCreateDto.setUserId(InLogin.getId());
            userCreateDto.setRoleList(loginToUser.getRoles());
            userCreateDto.setUserMail(InLogin.getUserMail());
            userCreateDto.setUserName(InLogin.getUserName());
            return new ResponseEntity<>(userCreateDto,HttpStatus.OK);
        }
        userCreateDto.setMessage("User successfully login");
        userCreateDto.setUserId(InLogin.getId());
        userCreateDto.setCompanyId(userToCompanyId.getId());
        userCreateDto.setRoleList(loginToUser.getRoles());
        userCreateDto.setUserMail(InLogin.getUserMail());
        userCreateDto.setUserName(InLogin.getUserName());
        return new ResponseEntity<>(userCreateDto,HttpStatus.OK);

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


