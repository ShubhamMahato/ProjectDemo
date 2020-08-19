package com.example.microservices.orderusergroceries.dao;


import com.example.microservices.orderusergroceries.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    User findByEmail(String email);

    User findById(long userId);

    



}
