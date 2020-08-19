package com.example.microservices.loginregisteruser.services.impl;


import com.example.microservices.loginregisteruser.dao.UserDao;
import com.example.microservices.loginregisteruser.exception.UserNotFoundException;
import com.example.microservices.loginregisteruser.model.Grocery;
import com.example.microservices.loginregisteruser.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("START:: JwtUserDetailsService :: loadUserByUsername");

        User user =userDao.findByEmail(username);
        if (user == null) {
            System.out.println("User not found exception");
            throw new UserNotFoundException("User not found with username: " + username);
        }
        logger.info("END:: JwtUserDetailsService :: loadUserByUsername");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }
}
