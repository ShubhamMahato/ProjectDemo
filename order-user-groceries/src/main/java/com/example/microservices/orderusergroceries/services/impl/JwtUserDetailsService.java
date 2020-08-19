package com.example.microservices.orderusergroceries.services.impl;


import com.example.microservices.orderusergroceries.dao.UserDao;
import com.example.microservices.orderusergroceries.exception.UserNotFoundException;
import com.example.microservices.orderusergroceries.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userDao.findByEmail(username);
        if (user == null) {
            System.out.println("User not found exception");
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }
}
