/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.utill.Response;
import com.mycompany.utill.ErrorReport;
import com.mycompany.dao.UserDao;
import com.mycompany.bean.User;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        } else {
            return new Response<>(false, "users not found");
        }
    }

    @PostMapping("/login")
    public Response<?> login(@Param("email") String email, @Param("password") String password) {
        User user = userDao.findUserByEmail(email, password);
        if (user != null) {
            return new Response<>(true, user);
        } else {
            return new Response<>(false, "invaild email or Password");
        }
    }

    @PostMapping("/register")
    public Response<?> register(
            @Param("email") String email,
            @Param("password") String password,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("birthdate") Date birthdate) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthdate(birthdate);
        try {
            User savedUser = userDao.save(user);
            return new Response<>(true, savedUser);

        } catch (javax.validation.ConstraintViolationException e) {
            List<ErrorReport> report = new ArrayList<>();
            for (ConstraintViolation cv : e.getConstraintViolations()) {
                report.add(new ErrorReport(cv.getPropertyPath().toString(), cv.getMessage()));
            }
            return new Response<>(false, report);
        }

    }
}