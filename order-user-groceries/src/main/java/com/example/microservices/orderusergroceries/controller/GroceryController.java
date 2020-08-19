package com.example.microservices.orderusergroceries.controller;

import com.example.microservices.orderusergroceries.exception.GroceryNotFoundException;
import com.example.microservices.orderusergroceries.model.Grocery;
import com.example.microservices.orderusergroceries.services.GroceriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grocery")
public class GroceryController {

    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);
    @Autowired
    GroceriesService groceriesService;

    @PostMapping("/addgrocery")
    ResponseEntity<Grocery> addGrocery(@RequestHeader("Authorization")String token,@RequestBody Grocery grocery) {
        logger.info("START:: GroceryController :: addGrocery");
       return new ResponseEntity<Grocery>(groceriesService.addGrocey(token, grocery), HttpStatus.OK);
    }

    @GetMapping("/getgroceries")
    ResponseEntity<List<Grocery>> getGrocery(@RequestHeader("Authorization")String token) {
        logger.info("START:: GroceryController :: getGrocery");
        return new ResponseEntity<>(groceriesService.getGroceries(token), HttpStatus.OK);
    }

    @DeleteMapping("/deletegrocery/{id}")
    ResponseEntity<String> deleteGrocery(@RequestHeader("Authorization")String token,@PathVariable Integer id){
        logger.info("START:: GroceryController :: deleteGrocery");

        try {
            return new ResponseEntity<>(groceriesService.deleteGrocery(token,id), HttpStatus.OK);
        } catch (Exception e) {
            throw new GroceryNotFoundException("No grocery available");
        }
    }


}
