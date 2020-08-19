package com.example.microservices.loginregisteruser.services;

import com.example.microservices.loginregisteruser.model.User;

public interface UserService {
    User findByEmail(String email);
    User signUp(User user);
    Boolean ValidateOtp(String email,String otp);
    User login(String email, String password);
    void deleteAllUsers();
    User getUserInfo(String token);
    User updateUserDetails(String token,User user);
}
