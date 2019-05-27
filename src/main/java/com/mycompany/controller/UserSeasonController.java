/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.User;
import com.mycompany.bean.UserSeason;
import com.mycompany.dao.SeasonDao;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonDao;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    
    @Autowired
    private SeasonDao seasonDao;

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
    
     @GetMapping("/getScore")
    public Response<?> score(@Param("user_id") Integer userId, @Param("season_id") Integer seasonId) {
         List<UserSeason> score = userSeasonDao.getUserScore(userId , seasonId);
        if (score.iterator().hasNext()) {

            List<UserSeason> userSeasons = new ArrayList<>();
            for (UserSeason s : score) {
                UserSeason us = new UserSeason();
                //us.setSeason(s.getSeason());
                us.setScore(s.getScore());
                us.setTripCount(s.getTripCount());
                //us.setUserSeasonPK(s.getUserSeasonPK());
                
                userSeasons.add(us);
            }

            return new Response<>(true, userSeasons);

        } else {
            return new Response<>(false, "no score for this user");
        }

    }
    
    @GetMapping("/userSeasons")
    public Response<?> getUserSeasons(@Param("userId") Integer userId) {
      
      List<UserSeason> seasons = userSeasonDao.getUserSeasons(userId);
        if (seasons.iterator().hasNext()) {

            List<UserSeason> userSeasons = new ArrayList<>();
            for (UserSeason s : seasons) {
                UserSeason season = new UserSeason();
                season.setSeason(s.getSeason());
                season.setUserSeasonPK(s.getUserSeasonPK());
                
                userSeasons.add(season);
            }

            return new Response<>(true, userSeasons);

        } else {
            return new Response<>(false, "no Seasons for this user");
        }

    }
}