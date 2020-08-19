package com.example.microservices.loginregisteruser.controller;

import com.example.microservices.loginregisteruser.config.JwtTokenUtil;
import com.example.microservices.loginregisteruser.model.*;
import com.example.microservices.loginregisteruser.services.UserService;
import com.example.microservices.loginregisteruser.services.impl.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/userdetails")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);

    @Autowired
    UserService userService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public User userSignUp(@RequestBody User user){
        logger.info("START:: UserController :: userSignUp");
        userService.signUp(user);
        logger.info("END:: UserController :: userSignUp");

        return user;
    }

    @PostMapping("/validateOtp")
    public Boolean validateOtp(@RequestBody OtpRequest otp){
        logger.info("START:: UserController :: validateOtp");

        if(userService.ValidateOtp(otp.getEmail(),otp.getOtp())==true) {
            logger.info("END:: UserController :: validateOtp");
            return true;
        }
        else {
            logger.info("END:: UserController :: validateOtp");
            return false;
        }

    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(HttpServletResponse response,@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.info("START:: UserController :: createAuthenticationToken");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if(userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword())==null){
            throw new UsernameNotFoundException(authenticationRequest.getUsername());
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("END:: UserController :: createAuthenticationToken");
        return ResponseEntity.ok(new JwtResponseWithUsername(token,userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword()).getName()));
    }





}
