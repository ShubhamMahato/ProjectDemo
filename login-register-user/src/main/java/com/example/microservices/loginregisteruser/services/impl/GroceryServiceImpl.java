package com.example.microservices.loginregisteruser.services.impl;

import com.example.microservices.loginregisteruser.config.JwtTokenUtil;
import com.example.microservices.loginregisteruser.dao.UserDao;
import com.example.microservices.loginregisteruser.exception.ExistingUserException;
import com.example.microservices.loginregisteruser.model.Grocery;
import com.example.microservices.loginregisteruser.model.User;
import com.example.microservices.loginregisteruser.services.GroceryService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class GroceryServiceImpl implements GroceryService {
    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    WebClient.Builder webBuilder;


    @Autowired
    UserDao userDao;

    @Override
    @HystrixCommand(fallbackMethod = "gofallbackadd")
    public Mono<Grocery> addGrocery(String token, Grocery grocery) {
        logger.info("START:: GroceryServiceImpl :: addgrocery");
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));
        if(userDao.findByEmail(userEmail)==null) {
            logger.error("ERROR:: GroceryServiceImpl :: addgrocery");
            throw new ExistingUserException("Please login again");
        }
        else{
            logger.info("END:: GroceryServiceImpl :: addgrocery");
            return webBuilder.build()
                    .post()
                    .uri("http://order-user-groceries/grocery/addgrocery")
                    .body(Mono.just(grocery), Grocery.class)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Grocery.class);
        }

    }

    @Override
    @HystrixCommand(fallbackMethod = "gofallbackgetGroceries")
    public Flux<Grocery> getGroceries(String token) {
        logger.info("START:: GroceryServiceImpl :: getGroceries");
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));
        if(userDao.findByEmail(userEmail)==null) {
            logger.error("ERROR:: GroceryServiceImpl :: getGroceries");
            throw new ExistingUserException("Please login again");
        }
        else {
            logger.info("END:: GroceryServiceImpl :: getGroceries");
            return webBuilder.build()
                    .get()
                    .uri("http://order-user-groceries/grocery/getgroceries")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToFlux(Grocery.class);
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "gofallback")
    public Mono<String> deleteGrocery(String token, Integer id) {
        logger.info("START:: GroceryServiceImpl :: deleteGrocery");
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));

        if(userDao.findByEmail(userEmail)==null) {
            logger.error("ERROR:: GroceryServiceImpl :: deleteGrocery");
            throw new ExistingUserException("Please login again");
        }

        else {
            logger.info("END:: GroceryServiceImpl :: deleteGrocery");
           return webBuilder.build()
                   .delete()
                   .uri("http://order-user-groceries/grocery/deletegrocery/"+id)
                   .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(String.class);
        }
    }
    @SuppressWarnings("unused")
    private Mono<String> gofallback(String token, Integer id) {
        logger.debug("START:: GroceryServiceImpl :: gofallback");
        System.out.println("Grocery Service is down!!! fallback route enabled...");
        logger.debug("END:: GroceryServiceImpl :: gofallback");
        return Mono.empty();
    }
    @SuppressWarnings("unused")
    private Flux<Grocery> gofallbackgetGroceries(String token){
        logger.debug("START:: GroceryServiceImpl :: gofallbackgetGroceries");
        System.out.println("Grocery Service is down!!! fallback route enabled...");
        logger.debug("END:: GroceryServiceImpl :: gofallbackgetGroceries");
        return Flux.empty();
    }
    @SuppressWarnings("unused")
    private Mono<Grocery> gofallbackadd(String token, Grocery grocery,Throwable exception) {
        logger.debug("START:: GroceryServiceImpl :: gofallbackadd");
        System.out.println("Grocery Service is down!!! fallback route enabled..."+exception);
        logger.debug("END:: GroceryServiceImpl :: gofallbackadd");
        return Mono.empty();
    }
}
