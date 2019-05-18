/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Car;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSeason;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonDao;
import com.mycompany.utill.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tahoon
 */
@RestController
@RequestMapping("/userSeason")
public class UserSeasonController {
    
    @Autowired
    private UserSeasonDao userSeasonDao;
    
    @Autowired
    private UserDao userDao;

    public void setUserSeasonDao(UserSeasonDao userSeasonDao) {
        this.userSeasonDao = userSeasonDao;
    }
    
    @GetMapping("/getUsers")
    public Response<?> getUsers() {
        Iterable<User> users = userDao.findAll();
        if (users.iterator().hasNext()) {
            return new Response<>(true, users);
        } else {
            return new Response<>(false, "users not found");
        }

    }
    
    
    
}
