package com.example.microservices.orderusergroceries.services.impl;

import com.example.microservices.orderusergroceries.config.Constants;
import com.example.microservices.orderusergroceries.config.JwtTokenUtil;
import com.example.microservices.orderusergroceries.dao.GroceryDao;
import com.example.microservices.orderusergroceries.dao.UserDao;
import com.example.microservices.orderusergroceries.exception.ExistingUserException;
import com.example.microservices.orderusergroceries.exception.GroceryNotFoundException;
import com.example.microservices.orderusergroceries.model.Grocery;
import com.example.microservices.orderusergroceries.services.GroceriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroceriesServiceImpl implements GroceriesService {
    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroceryDao groceryDao;
    @Override
    public Grocery addGrocey(String token, Grocery grocery) {
        logger.info("START:: GroceryServiceImpl :: addGrocey");
        Grocery grocer =null;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));
        if(userDao.findByEmail(userEmail)==null) {
            throw new ExistingUserException("Please login again");
        }
        else
        {
            System.out.println(grocery.getAvailableStocks()+""+grocery.getOrigin()+""+grocery.getGroceryType()+""+grocery.getGroceryName());
            this.checkGroceryValues(grocery);
            grocer=groceryDao.save(grocery);
        }
        logger.info("END:: GroceryServiceImpl :: addGrocey");

        return grocer;
    }

    @Override
    public List<Grocery> getGroceries(String token) {
        logger.info("START:: GroceryServiceImpl :: getGroceries");
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));
        if(userDao.findByEmail(userEmail)==null) {
            throw new ExistingUserException("Please login again");
        }
        if(groceryDao.findAll().size()==0){
            throw new GroceryNotFoundException("Grocerys not available");
        }
        logger.info("END:: GroceryServiceImpl :: getGroceries");
        return groceryDao.findAll();
    }



    @Override
    public String deleteGrocery(String token, Integer id) throws Exception {
        logger.info("START:: GroceryServiceImpl :: deleteGrocery");
        Grocery grocer=null;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token.substring(7));
        if(userDao.findByEmail(userEmail)==null) {
            throw new ExistingUserException("Please login again");
        }

        try {
             grocer = groceryDao.findById(id).orElseThrow(() -> new Exception("No grocery available - " + id));
        }
        catch(NoSuchElementException e){
            throw new GroceryNotFoundException("No grocery available");
        }
        groceryDao.delete(grocer);
        logger.info("END:: GroceryServiceImpl :: deleteGrocery");
        return Constants.DELETED;
    }

    private Boolean checkGroceryValues(Grocery grocery){
        logger.info("START:: GroceryServiceImpl :: checkGroceryValues");

        if(grocery.getGroceryName().length()<=2){
            throw new GroceryNotFoundException("Grocery Name Not Proper");
        }
        if(grocery.getGroceryType().length()<=2){
            throw new GroceryNotFoundException("Grocery type Not Proper");
        }
        if(grocery.getOrigin().length()<=2){
            throw new GroceryNotFoundException("Grocery origin Not Proper");
        }
        logger.info("EMD:: GroceryServiceImpl :: checkGroceryValues");
        return false;
    }
}
