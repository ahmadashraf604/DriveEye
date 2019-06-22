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
import com.mycompany.bean.UserSeasonBadge;
import com.mycompany.dto.BadgeDto;
import com.mycompany.dto.CarDto;
import com.mycompany.dto.CityDto;
import com.mycompany.dto.UserDto;
import com.mycompany.dto.UserSeasonBadgeDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    private SeasonController seasonController;
    
    @Autowired
    private UserSeasonController userSeasonController;

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

    @Transactional
    @GetMapping("/info/{id}")
    public Response<?> getUserInfo(@PathVariable int id) {

        if (userDao.existsById(id)) {
            User user = userDao.findById(id).get();
            UserDto userDto = new UserDto();
            userDto.setBirthdate(user.getBirthdate());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setLevel(user.getLevel());
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getEmail());
            userDto.setImage("/user/image/"+user.getUserId());
            userDto.setCar(new CarDto(user.getCarId().getCarId(), user.getCarId().getBrand(), user.getCarId().getModel()));
            userDto.setCity(new CityDto(user.getCityId().getCityId(), user.getCityId().getName()));
            Collection<UserSeasonBadge> userSeasonBadgeCollection = user.getUserSeasonBadgeCollection();
            System.out.println(userSeasonBadgeCollection.size()+"size ");
            ArrayList <UserSeasonBadgeDto> badgesList = new ArrayList<UserSeasonBadgeDto>();

            for (UserSeasonBadge badge : userSeasonBadgeCollection) {
                BadgeDto badgeDto = new BadgeDto(badge.getBadge().getBadgeId(),"/userBadge/image/" + badge.getBadge().getBadgeId(),
                        badge.getBadge().getName(), badge.getBadge().getType());
                badgesList.add(new UserSeasonBadgeDto(badgeDto));
            }
            userDto.setUserSeasonBadgeCollection(badgesList);

            return new Response<>(true, userDto);
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

    @Transactional
    @PostMapping("/login")
    public Response<?> login(@Param("email") String email, @Param("password") String password) {
        User user = userDao.findUserByEmail(email, password);
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setLevel(user.getLevel());
            userDto.setEmail(user.getEmail());
            userDto.setBirthdate(user.getBirthdate());
            userDto.setImage("/user/image/"+user.getUserId());
            userDto.setCar(new CarDto(user.getCarId().getCarId()));
            userDto.setCity(new CityDto(user.getCityId().getCityId(), user.getCityId().getName()));
            return new Response<>(true, userDto);
        } else {
            return new Response<>(false, "invaild email or Password");
        }
    }

    @Transactional
    @PostMapping("/register")
    public Response<?> register(
            @Param("email") String email,
            @Param("password") String password,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Param("birthdate") Date birthdate,
            @Param("cityId") int cityId,
            @RequestParam("image") MultipartFile image) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        birthdate.setHours(22);
        user.setBirthdate(birthdate);
        user.setLevel(0);
        try {
            user.setImage(image.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setCityId(cityContoller.findCityById(cityId));
        try {
            User savedUser = userDao.save(user);
            userSeasonController.addUserSeason(savedUser.getUserId(), seasonController.getCurrentSeason(), 0);
            UserDto userDto = new UserDto();
            userDto.setUserId(savedUser.getUserId());
            userDto.setFirstName(savedUser.getFirstName());
            userDto.setLastName(savedUser.getLastName());
            userDto.setLevel(savedUser.getLevel());
            userDto.setEmail(savedUser.getEmail());
            userDto.setBirthdate(savedUser.getBirthdate());
            userDto.setImage("/user/image/"+savedUser.getUserId());
//            userDto.setCar(new CarDto(savedUser.getCarId().getCarId()));
            userDto.setCity(new CityDto(savedUser.getCityId().getCityId(), savedUser.getCityId().getName()));
            return new Response<>(true, userDto);
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

    @Transactional
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
    
    @GetMapping("/image/{userId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer userId) {
        // Load file from database
        User user = userDao.findById(userId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getFirstName() + "\"")
                .body(new ByteArrayResource(user.getImage()));
    }
    
    @PutMapping("/update/{userId}")
    public Response<?> deleteUser(@PathVariable Integer userId, @PathVariable String email) {
        System.out.println("/delete/{userId}"+ userId);
        userDao.updateEmail(email,userId);
        return new Response<>(true, "update sucessfully");
    }
}
