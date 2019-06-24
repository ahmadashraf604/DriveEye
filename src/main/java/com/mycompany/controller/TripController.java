/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Trip;
import com.mycompany.bean.User;
import com.mycompany.dao.TripDao;
import com.mycompany.dao.UserDao;
import com.mycompany.utill.Response;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("trip")
public class TripController {

    @Autowired
    TripDao tripDao;

    @Autowired
    UserController userController;

    @Autowired
    UserLeagueContoller userLeagueContoller;

    @Autowired
    UserSeasonController userSeasonController;

    @Autowired
    UserDao userDao;

    public void setTripDao(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/getAll")
    public Response<?> getAll() {
        Iterable<Trip> trips = tripDao.findAll();
        if (trips.iterator().hasNext()) {
            return new Response<>(true, trips);
        } else {
            return new Response<>(false, "no trips found");
        }
    }

    @GetMapping("/get/{userId}")
    public Response<?> findByUserId(@PathVariable Integer userId) {
        if (userController.existUserById(userId) != null) {
            Iterable<Trip> trips = tripDao.findAll();
            if (trips.iterator().hasNext()) {
                return new Response<>(true, trips);
            }
            return new Response<>(false, "no trips found");
        }
        return new Response<>(false, "no such user by having id = " + userId);
    }

    @Transactional
    @PostMapping("/add")
    public Response<?> add(@Param("startPoint") String startPoint,
            @Param("endPoint") String endPoint,
            @Param("duration") Double duration,
            @Param("userId") Integer userId,
            @Param("score") Integer score) {

        User user = userController.existUserById(userId);
        if (user != null) {
            Trip trip = new Trip();
            trip.setTripId(getRandomId());
            trip.setDuration(duration);
            trip.setStartPoint(startPoint);
            trip.setEndPoint(endPoint);
            trip.setUserId(user);
            Trip savedTrip = tripDao.save(trip);
            if (savedTrip != null) {
                userLeagueContoller.increaseScore(user, score);
                userSeasonController.increaseScore(user, score);
                userDao.updateLeve(userId, getDistance(duration));
                System.out.println("saved trip");
                return new Response<>(true, "add sucessfully");
            }
            return new Response<>(false, "add falid");
        }
        return new Response<>(false, "user ID doesn't exist");
    }

    @DeleteMapping("/delete/{tripId}")
    public Response<?> delete(@PathVariable Integer tripId) {
        Trip trip = findTripById(tripId);
        if (trip != null) {
            tripDao.delete(trip);
            return new Response<>(true, "delete sucessfully");
        }
        return new Response<>(false, "delete falid, not such trip by this id");
    }

    public Trip findTripById(int tripId) {
        if (tripDao.existsById(tripId)) {
            return tripDao.findById(tripId).get();
        }
        return null;
    }

    public int getRandomId() {
        double randomDouble = Math.random() + Math.random() + Math.random();
        randomDouble = randomDouble * 5547;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    public Integer getDistance(double duration) {
        System.out.println("duration" + duration);
        System.out.println("(duration * .028)" + (duration * .028));
        return (int) (duration * .028);
    }
}
