package com.example.microservices.orderusergroceries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderUserGroceriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderUserGroceriesApplication.class, args);
	}

}
