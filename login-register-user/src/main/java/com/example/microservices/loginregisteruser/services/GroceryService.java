package com.example.microservices.loginregisteruser.services;

import com.example.microservices.loginregisteruser.model.Grocery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GroceryService {
    Mono<Grocery> addGrocery(String token, Grocery grocery);
    Flux<Grocery> getGroceries(String token);
    Mono<String> deleteGrocery(String token,Integer id);
}
