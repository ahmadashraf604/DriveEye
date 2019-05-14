/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ashraf_R
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userDao.findById(id).get();
    }
    @GetMapping("/get")
    public Iterable<User> getUsers() {
        return userDao.findAll();
    }
    
//    @RequestMapping("/get")
//    public String get() {
//        User user = new User();
//        user.setEmail("asdas");
//        user.setFirstName("sada");
//        user.setLastName("asdas");
//        userDao.save(user);
//        return "sdfsdf";
//    }

}
