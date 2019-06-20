/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Car;
import com.mycompany.utill.Response;
import com.mycompany.utill.ErrorReport;
import com.mycompany.dao.UserDao;
import com.mycompany.bean.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Ashraf_R
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityContoller cityContoller;

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

    @GetMapping("/getAll")
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
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Param("birthdate") Date birthdate,
            @Param("cityId") int cityId) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        birthdate.setHours(22);
        user.setBirthdate(birthdate);
        user.setLevel(0);
        user.setCityId(cityContoller.findCityById(cityId));
        try {
            User savedUser = userDao.save(user);
            return new Response<>(true, savedUser);

        } catch (javax.validation.ConstraintViolationException e) {
            List<ErrorReport> report = new ArrayList<>();
            for (ConstraintViolation cv : e.getConstraintViolations()) {
                report.add(new ErrorReport(cv.getPropertyPath().toString(), cv.getMessage()));
            }
            return new Response<>(false, report);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            if (e.getLocalizedMessage().contains("email_UNIQUE")) {
                return new Response<>(false, new ErrorReport("email", "email must be uniqe"));
            }
            return new Response<>(false, new ErrorReport("un none", e.getLocalizedMessage()));
        }

    }

    public User existUserById(int userId) {
        if (userDao.existsById(userId)) {
            return userDao.findById(userId).get();
        }
        return null;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    //@GetMapping(value= "edit/{id}")
    public Response<?> updateUser(@PathVariable String fName, @PathVariable String lName,
            @PathVariable String email, @PathVariable Byte image, @PathVariable String password, @PathVariable Integer id) {
        User user = new User();
        Optional<User> u = userDao.findById(id);
//        if(car.getCarId().equals(id)){

        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setImage(user.getImage());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        //user.setCityId(user.getCityId().getName());

//        userDao.updateUser(fName, lName, email, id, Byte.MIN_VALUE, password, id);
        return new Response<>(true, "car updated sucessfully");
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            userDao.updateImage(file.getBytes(), 1);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path("1")
                .toUriString();

        return new Response(true, fileDownloadUri + "\t" + file.getContentType() + "\t" + file.getSize());
    }

    @GetMapping("/downloadFile/{userId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer userId) {
        // Load file from database
        User user = userDao.findById(userId).get();
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getFirstName() + "\"")
                .body(new ByteArrayResource(user.getImage()));
    }
}
