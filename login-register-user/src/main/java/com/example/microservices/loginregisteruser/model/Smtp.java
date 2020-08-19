package com.example.microservices.loginregisteruser.model;

public class Smtp {
    private String host;
    private String username;
    private String password;
    private Integer port;
    private Boolean auth;
    private Boolean enable;

    public Smtp() {
    }

    public Smtp(String host, String username, String password, Integer port, Boolean auth, Boolean enable) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.auth = auth;
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
