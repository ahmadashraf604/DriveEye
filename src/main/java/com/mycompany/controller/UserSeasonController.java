/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Season;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSeason;
import com.mycompany.bean.UserSeasonPK;
import com.mycompany.dao.SeasonDao;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonDao;
import com.mycompany.dto.SeasonDto;
import com.mycompany.dto.UserSeasonDto;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<?> addUserSeason(@Param("user_id") Integer userId, @Param("season_id") Integer seasonId,
            @Param("score") Integer score) {
        if (userDao.existsById(userId)) {

            if (seasonDao.existsById(seasonId)) {
                UserSeason userSeason = new UserSeason();
                userSeason.setScore(score);
                userSeason.setTripCount(0);
                userSeason.setUser(userDao.findById(userId).get());
                userSeason.setSeason(seasonDao.findById(seasonId).get());
                UserSeasonPK pK = new UserSeasonPK();
                pK.setSeasonId(seasonId);
                pK.setUserId(userId);
                userSeason.setUserSeasonPK(pK);
                userSeasonDao.save(userSeason);

                return new Response<>(true, userSeason);
                // return new Response<>(true, "Season added sucessfully");
            }
        }
        return new Response<>(false, "ERROR");

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
        List<UserSeason> score = userSeasonDao.getUserScore(userId, seasonId);
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

    @GetMapping("/userSeasons/{userId}")
    public Response<?> getUserSeasons(@PathVariable("userId") Integer userId) {

        List<UserSeason> seasons = userSeasonDao.getUserSeasons(userId);
        if (seasons.iterator().hasNext()) {

            List<SeasonDto> userSeasons = new ArrayList<>();
            for (UserSeason s : seasons) {
                List<UserSeason> seasonScores = userSeasonDao.getSeasonScoreOrderd(s.getSeason().getSeasonId());
                int seasonHighScore = 0;
                int myRank = 0;
                if (seasonScores.size() > 0) {
                    seasonHighScore = seasonScores.get(0).getScore();
                    myRank = seasonScores.indexOf(s) + 1;
                }
                SeasonDto season = new SeasonDto(
                        s.getSeason().getSeasonId(),
                        s.getSeason().getStartDate(),
                        s.getSeason().getEndDate(),
                        seasonHighScore,
                        s.getScore(),
                        myRank
                );

                userSeasons.add(season);
            }

            return new Response<>(true, userSeasons);

        } else {
            return new Response<>(false, "no Seasons for this user");
        }
    }

    @GetMapping("/seasonUsers/{seasonId}")
    public Response<?> getSeasonUsers(@PathVariable("seasonId") Integer seasonId) {

        if (seasonDao.existsById(seasonId)) {
//            Collection<UserSeason> seasons = seasonDao.findById(seasonId).get().getUserSeasonCollection();
            List<UserSeason> seasonUsers = userSeasonDao.getSeasonScoreOrderd(seasonId);
            if (seasonUsers.iterator().hasNext()) {
                List<UserSeasonDto> seasonUsersDTOS = new ArrayList<>();
                int rank = 1;
                for (UserSeason s : seasonUsers) {
                    UserSeasonDto season = new UserSeasonDto(
                            s.getUser().getUserId(),
                            s.getUser().getFirstName() + " " + s.getUser().getLastName(),
                            s.getScore(),
                            rank
                    );
                    seasonUsersDTOS.add(season);
                    rank++;
                }
                return new Response<>(true, seasonUsersDTOS);
            }
        }
        return new Response<>(false, "no Seasons for this user");

    }

    void increaseScore(User user, Integer score) {
       userSeasonDao.increaseScore(score, user);
    }
}
