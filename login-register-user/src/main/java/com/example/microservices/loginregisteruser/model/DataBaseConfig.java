package com.example.microservices.loginregisteruser.model;

public class DataBaseConfig {
    private String username;
    private String url;
    private String password;
    private String driverClassName;

    public DataBaseConfig() {
    }

    public DataBaseConfig(String username, String url, String password, String driverClassName) {
        this.username = username;
        this.url = url;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
