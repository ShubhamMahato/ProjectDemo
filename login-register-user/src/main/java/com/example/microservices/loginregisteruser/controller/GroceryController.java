package com.example.microservices.loginregisteruser.controller;

import com.example.microservices.loginregisteruser.model.Grocery;
import com.example.microservices.loginregisteruser.services.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/grocery")
public class GroceryController {
    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);
    @Autowired
    GroceryService groceryService;

    @PostMapping("/addgrocery")
    public ResponseEntity<Mono<Grocery>>addgrocery(@RequestHeader("Authorization")String token, @RequestBody Grocery grocery){

        logger.info("START:: GroceryController :: addgrocery");
        return new ResponseEntity<Mono<Grocery>>(groceryService.addGrocery(token,grocery), HttpStatus.OK);

    }

    @GetMapping("/getgroceries/allgroceries")
    public ResponseEntity<Flux<Grocery>>getgroceries(@RequestHeader("Authorization")String token){
        logger.info("START:: GroceryController :: getgroceries");
        return new ResponseEntity<>(groceryService.getGroceries(token), HttpStatus.OK);
    }

    @DeleteMapping("/deletegroceries/delete/{id}")
    public ResponseEntity<Mono<String>>deletegroceries(@RequestHeader("Authorization")String token,@PathVariable Integer id){
        logger.info("START:: GroceryController :: deletegroceries");
        return new ResponseEntity<>(groceryService.deleteGrocery(token, id), HttpStatus.OK);
    }

}
