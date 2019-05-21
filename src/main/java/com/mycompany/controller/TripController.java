/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Trip;
import com.mycompany.dao.TripDao;
import com.mycompany.utill.Response;
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
    public Response<?> findByUserId(@PathVariable int userId) {
        if (userController.existUserById(userId) != null) {
            Iterable<Trip> trips = tripDao.findAll();
            if (trips.iterator().hasNext()) {
                return new Response<>(true, trips);
            }
            return new Response<>(false, "no trips found");
        }
        return new Response<>(false, "no such user by having id = " + userId);
    }

    @PostMapping("/add")
    public Response<?> add(
            @Param("startPoint") String startPoint,
            @Param("endtPoint") String endtPoint,
            @Param("duration") double duration,
            @Param("userId") int userId) {
        Trip trip = new Trip();
        trip.setDuration(duration);
        trip.setStartPoint(startPoint);
        trip.setEndPoint(endtPoint);
        trip.setUserId(userController.existUserById(userId));

        Trip savedTrip = tripDao.save(trip);
        if (savedTrip != null) {
            return new Response<>(true, "add sucessfully");
        }
        return new Response<>(false, "add falid");
    }

    @DeleteMapping("/{tripId}")
    public Response<?> delete(@PathVariable int tripId) {
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
}
