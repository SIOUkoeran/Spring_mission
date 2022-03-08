package com.example.mission3_basic.user.service;

import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface UserService {
    User.ResponseUser createUser(User.RequestUser requestUser);
    List<UserEntity> getUsers();
    User.ResponseUser getUser(String username);
    UserEntity getUserByIdReturnUserEntity(Long userId);
    UserEntity getUserReturnEntity(String username);
    User.SignUpResponse signUpUser(User.RequestSignupForm signupForm);
}
