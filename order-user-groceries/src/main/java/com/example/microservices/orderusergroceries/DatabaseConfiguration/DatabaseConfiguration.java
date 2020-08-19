package com.example.microservices.orderusergroceries.DatabaseConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfiguration {

    @Autowired
    Config databaseConfiguration;

    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(databaseConfiguration.getDriver());
        dataSourceBuilder.url(databaseConfiguration.getUrl());
        dataSourceBuilder.username(databaseConfiguration.getUsername());
        dataSourceBuilder.password(databaseConfiguration.getPassword());
        return dataSourceBuilder.build();
    }
}