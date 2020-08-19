package com.example.microservices.loginregisteruser.model;

import org.springframework.beans.factory.annotation.Value;

public class JpaConfiguration {

    @Value("${spring-service.generate}")
    private String generate;

    @Value("${spring-service.auto }")
    private String auto;

    public JpaConfiguration() {
    }

    public JpaConfiguration(String generate, String auto) {
        this.generate = generate;
        this.auto = auto;
    }

    public String getGenerate() {
        return generate;
    }

    public void setGenerate(String generate) {
        this.generate = generate;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }
}
