/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Coaching;
import com.mycompany.bean.League;
import com.mycompany.dao.CoachingDao;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdelrahman
 */
@RestController
@RequestMapping("coach")
public class CoachingContoller {

    @Autowired
    CoachingDao coachingDao;

    @GetMapping("add")
    public Response<?> add(@Param String description) {
        Coaching coaching = new Coaching();
        coaching.setDescription(description);
        coachingDao.save(coaching);
        return new Response<>(true, "add");
    }

    @GetMapping("/{id}")
    public Response<?> getById(@PathVariable int id) {
        return new Response<>(true, coachingDao.findById(id).get());
    }
    @GetMapping("get")
      public Response<?> getAllLeague( ) {
        Iterable<Coaching> coachings = coachingDao.findAll();
        if (coachings.iterator().hasNext()) {   
            return new Response<>(true, coachings);

        } else {
            return new Response<>(false, "no cities found ");
        }
    }

}
