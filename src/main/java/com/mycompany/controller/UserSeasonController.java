/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.User;
import com.mycompany.bean.UserSeason;
import com.mycompany.bean.UserSeasonPK;
import com.mycompany.dao.SeasonDao;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonDao;
import com.mycompany.dto.HomeDto;
import com.mycompany.dto.SeasonDto;
import com.mycompany.dto.UserSeasonDto;
import com.mycompany.utill.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private UserController userController;

    @Autowired
    private SeasonDao seasonDao;

    public void setUserSeasonDao(UserSeasonDao userSeasonDao) {
        this.userSeasonDao = userSeasonDao;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<?> addUserSeason(@Param("userId") Integer userId, @Param("season_id") Integer seasonId,
            @Param("score") Integer score) {
        User user = userController.existUserById(userId);
        if (user != null) {
            if (seasonDao.existsById(seasonId)) {
                UserSeason userSeason = new UserSeason();
                userSeason.setScore(score);
                userSeason.setTripCount(0);
                userSeason.setUser(user);
                userSeason.setSeason(seasonDao.findById(seasonId).get());
                UserSeasonPK pK = new UserSeasonPK();
                pK.setSeasonId(seasonId);
                pK.setUserId(userId);
                userSeason.setUserSeasonPK(pK);
                userSeasonDao.save(userSeason);
//                return new Response<>(true, userSeason);
                return new Response<>(true, "Season added sucessfully");
            }
        }
        return new Response<>(false, "user doesn't");

    }

    @GetMapping("/getScore")
    public Response<?> score(@Param("userId") Integer userId, @Param("seasonId") Integer seasonId) {
        List<UserSeason> score = userSeasonDao.getUserScore(userId, seasonId);
        if (score.iterator().hasNext()) {

            List<UserSeason> userSeasons = new ArrayList<>();
            for (UserSeason s : score) {
                UserSeason us = new UserSeason();
                us.setScore(s.getScore());
                us.setTripCount(s.getTripCount());
                userSeasons.add(us);
            }

            return new Response<>(true, userSeasons);

        } else {
            return new Response<>(false, "no score for this user");
        }

    }

    @Transactional
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
                    UserSeason userSeason = seasonScores.get(0);
                    seasonHighScore = userSeason.getScore();
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

    @Transactional
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

    @Transactional
    @GetMapping("/seasonUsersScore/{userId}")
    public Response<?> getSeasonUserssCORE(@PathVariable Integer userId) {

//        if (userDao.existsById(userId)) {
        List<UserSeason> scores = userSeasonDao.getScore(userId);
        if (scores.iterator().hasNext()) {
            UserSeason userSeason = scores.get(0);
            HomeDto dto = new HomeDto();
            dto.setScore(userSeason.getScore());
//                        System.out.println(userSeason.getSeason().getEndDate());
            dto.setDaysLeft(getDaysLeft(userSeason.getSeason().getEndDate()));
            dto.setSeasonNUmber(userSeason.getSeason().getSeasonId());
            dto.setUserLevel(userSeason.getUser().getLevel());
            return new Response<>(true, dto);
        }
//        }
        return new Response<>(false, "no Seasons for this user");

    }

    private long getDaysLeft(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = date;
        Date secondDate = new Date();
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

   
    @DeleteMapping("/delete/{userId}/{seasonId}")
    public Response<?> deleteUserSeason(@PathVariable Integer userId, @PathVariable Integer seasonId) {
        userSeasonDao.deleteUserSeason(userId, seasonId);
        return new Response<>(false, "no Seasons for this user");
    }
}
