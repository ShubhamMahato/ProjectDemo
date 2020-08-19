package com.example.microservices.orderusergroceries.model;

import javax.persistence.*;

@Entity
@Table(name="Grocery")
public class Grocery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroceryId")
    private Integer GroceryId;

    @Column(name = "grocery_type")
    private String groceryType;

    @Column(name="grocery_name")
    private String groceryName;

    @Column(name = "origin")
    private String origin;

    @Column(name="avaliable_stocks")
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
