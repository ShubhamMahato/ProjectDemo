package com.example.microservices.loginregisteruser.model;

import javax.persistence.*;

public class Grocery {
    private Integer GroceryId;

    private String groceryType;

    private String groceryName;

    private String origin;

    private Integer availableStocks;

    public Grocery() {
    }

    public Grocery(Integer groceryId, String groceryType, String groceryName, String origin, Integer availableStocks) {
        GroceryId = groceryId;
        this.groceryType = groceryType;
        this.groceryName = groceryName;
        this.origin = origin;
        this.availableStocks = availableStocks;
    }

    public Integer getGroceryId() {
        return GroceryId;
    }

    public void setGroceryId(Integer groceryId) {
        GroceryId = groceryId;
    }

    public String getGroceryType() {
        return groceryType;
    }

    public void setGroceryType(String groceryType) {
        this.groceryType = groceryType;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getAvailableStocks() {
        return availableStocks;
    }

    public void setAvailableStocks(Integer availableStocks) {
        this.availableStocks = availableStocks;
    }
}
