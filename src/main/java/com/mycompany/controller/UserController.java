/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Response<?> getUser(@PathVariable int id) {
        if (userDao.existsById(id)) {
            return new Response<>(true, userDao.findById(id).get());
        } else {
            return new Response<>(false, "user not found");
        }
    }

    @GetMapping("/get")
    public Response<?> getUsers() {
        Iterable<User> users = userDao.findAll();
        if (users.iterator().hasNext()) {
            return new Response<>(true, users);
        }else{
            return new Response<>(false, "users not found");
        }
    }

//    @PostMapping("/login")
//    public String login() {
//        User user = new User();
//        user.setEmail("asdas");
//        user.setFirstName("sada");
//        user.setLastName("asdas");
//        userDao.save(user);
//        return "sdfsdf";
//    }
}
