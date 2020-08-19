package com.example.microservices.loginregisteruser.controller;

import com.example.microservices.loginregisteruser.DatabaseConfiguration.Config;
import com.example.microservices.loginregisteruser.model.DataBaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseConfig {

    @Autowired
    Config databaseConfiguration;


    @GetMapping("/database")
    public DataBaseConfig getinfo(){
        return new DataBaseConfig(databaseConfiguration.getUsername(),databaseConfiguration.getUrl(),databaseConfiguration.getPassword(),databaseConfiguration.getDriver());
    }
}
