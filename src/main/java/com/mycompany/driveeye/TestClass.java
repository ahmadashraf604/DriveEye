/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.driveeye;

import com.mycompany.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ashraf_R
 */
@RestController
public class TestClass {

//    @Autowired
//    private UserDao userDao;
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//    
//    @GetMapping("/get")
//    public User getUser() {
//        User user = new User(6556, "asdnasmhdj,asjd");
//        return user;
//    }
    @RequestMapping("/getUser")
    public String getUser() {
        return "sdfsdf";
    }

}
