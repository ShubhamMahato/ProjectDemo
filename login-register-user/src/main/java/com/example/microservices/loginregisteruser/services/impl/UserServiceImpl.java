package com.example.microservices.loginregisteruser.services.impl;

import com.example.microservices.loginregisteruser.config.Constants;
import com.example.microservices.loginregisteruser.config.JwtTokenUtil;
import com.example.microservices.loginregisteruser.dao.UserDao;
import com.example.microservices.loginregisteruser.exception.ExistingUserException;
import com.example.microservices.loginregisteruser.exception.UserNotFoundException;
import com.example.microservices.loginregisteruser.model.Grocery;
import com.example.microservices.loginregisteruser.model.User;
import com.example.microservices.loginregisteruser.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(Grocery.class);

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User findByEmail(String email) {
        User user =userDao.findByEmail(email);
        return user;
    }

    @Override
    public User signUp(User user) {
        logger.info("START:: UserServiceImpl :: signUp");

        User userCheck =this.findByEmail(user.getEmail());
        if(this.findByEmail(user.getEmail())!=null&& userCheck.getValidated()==false){
            SendEmail sendEmail = new SendEmail();
            sendEmail.userRegistrationEmail(user);
            logger.info("END:: UserServiceImpl :: signUp");

            return user;
        }

        if(this.findByEmail(user.getEmail())!=null&& user.getValidated()==true){
            logger.info("END:: UserServiceImpl :: signUp");

            throw new ExistingUserException(Constants.EMAIL_EXISTS);
        }
        if(user.getPassword().length()<8){
            logger.info("END:: UserServiceImpl :: signUp");
            throw new ExistingUserException(Constants.PASSWORD_CRITERIA_NOTMATCHED);
        }
        if(this.findByEmail(user.getEmail())==null&& user.getValidated()==false) {
            SendEmail sendEmail = new SendEmail();
            sendEmail.userRegistrationEmail(user);
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            userDao.save(user);
        }
        logger.info("END:: UserServiceImpl :: signUp");
        return user;
    }

    @Override
    public Boolean ValidateOtp(String email,String otp) {
        logger.info("START:: UserServiceImpl :: ValidateOtp");

        if(this.findByEmail(email)==null){
            throw new ExistingUserException("User Doesn't Exist");
        }

        User user =this.findByEmail(email);
        if(user.getOtp().equalsIgnoreCase(otp)){
            user.setValidated(true);
            userDao.save(user);
            logger.info("END:: UserServiceImpl :: ValidateOtp");
            return true;
        }
        return false;
    }

    @Override
    public User login(String email, String password){
        logger.info("START:: UserServiceImpl :: login");

        User userDocument = userDao.findByEmail(email);
        if( userDocument !=null) {
            if (bcryptEncoder.matches(password, userDocument.getPassword()))
            {
                if(userDocument.getValidated()==false){
                    throw new ExistingUserException("Not Validated");
                }
                logger.info("END:: UserServiceImpl :: login");

                return userDocument;
            }
        }
        return null;
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @Override
    public User getUserInfo(String token) {
        logger.info("START:: UserServiceImpl :: getUserInfo");
        User user =null;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else
        {
            user = userDao.findByEmail(userEmail);
            user.setPassword(null);
        }
        logger.info("END:: UserServiceImpl :: getUserInfo");

        return user;
    }

    @Override
    public User updateUserDetails(String token,User user) {
        logger.info("START:: UserServiceImpl :: updateUserDetails");

        User userinfo =null;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            userinfo = userDao.findByEmail(userEmail);
            if(user.getEmail().equalsIgnoreCase(userinfo.getEmail())){
                userinfo.setValidated(false);
                SendEmail sendEmail = new SendEmail();
                sendEmail.userRegistrationEmail(user);
            }
            userinfo.setName(user.getName());
            userinfo.setCity(user.getCity());
            userDao.save(userinfo);
            logger.info("END:: UserServiceImpl :: updateUserDetails");

            return userinfo;
        }
    }

}
