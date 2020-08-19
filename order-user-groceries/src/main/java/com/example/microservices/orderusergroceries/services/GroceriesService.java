package com.example.microservices.orderusergroceries.services;

import com.example.microservices.orderusergroceries.model.Grocery;

import java.util.List;

public interface GroceriesService {
    Grocery addGrocey(String token,Grocery grocery);
    List<Grocery> getGroceries(String token);
    String deleteGrocery(String token,Integer id) throws Exception;

}
