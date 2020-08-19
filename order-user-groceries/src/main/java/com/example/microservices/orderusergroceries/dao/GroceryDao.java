package com.example.microservices.orderusergroceries.dao;

import com.example.microservices.orderusergroceries.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroceryDao extends JpaRepository<Grocery, Integer> {
    List<Grocery> findByGroceryName(String groceryName);
    List<Grocery> findByGroceryType(String groceryType);
}
