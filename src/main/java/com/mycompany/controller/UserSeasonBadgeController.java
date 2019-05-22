/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.UserSeasonBadge;
import com.mycompany.bean.UserSeasonBadgePK;
import com.mycompany.dao.BadgeDao;
import com.mycompany.dao.SeasonDao;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonBadgeDao;
import com.mycompany.utill.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdo Amin
 */
@RestController
@RequestMapping("/userBadge")
public class UserSeasonBadgeController {

    @Autowired
    private UserSeasonBadgeDao userSeasonBadgeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SeasonDao seasonDao;
    @Autowired
    private BadgeDao badgeDao;

    public void setUserSeasonBadgeDao(UserSeasonBadgeDao userSeasonBadgeDao) {
        this.userSeasonBadgeDao = userSeasonBadgeDao;
    }

    @GetMapping("/{userId}")
    public Response<?> getUserBadgeInAllSeason(@PathVariable int userId) {
        Iterable<UserSeasonBadge> iterable = userSeasonBadgeDao.findByUserId(userId);
        if (iterable.iterator().hasNext()) {
            return new Response<>(true, iterable);
        } else {
            return new Response<>(false, "user not have badge");
        }
    }

    @GetMapping("/{userId}/{seasonId}")
    public Response<?> getUserBadgeInSeason(@PathVariable int userId, @PathVariable int seasonId) {
        Iterable<UserSeasonBadge> iterable = userSeasonBadgeDao.findByUserIdAndSeasonId(userId, seasonId);
        if (iterable.iterator().hasNext()) {
            return new Response<>(true, iterable);
        } else {
            return new Response<>(false, "user not have badge in this season");
        }
    }

    @GetMapping("/add/{userId}")
    public Response<?> addUserBadgeInSeason(
            @PathVariable int userId,
            @Param("seasonId") int seasonId,
            @Param("badgeId") int badgeId) {
        System.out.println("userId : " + userId);
        System.out.println("seasonId : " + seasonId);
        System.out.println("badgeId : " + badgeId);

        if (userDao.existsById(userId)) {

            if (seasonDao.existsById(seasonId)) {
                if (badgeDao.existsById(badgeId)) {
                    UserSeasonBadge badge = new UserSeasonBadge();
                    badge.setUser(userDao.findById(userId).get());
                    badge.setSeason(seasonDao.findById(seasonId).get());
                    badge.setBadge(badgeDao.findById(badgeId).get());
                    UserSeasonBadgePK pK = new UserSeasonBadgePK();
                    pK.setBadgeId(badgeId);
                    pK.setSeasonId(seasonId);
                    pK.setUserId(userId);
                    badge.setUserSeasonBadgePK(pK);
                    userSeasonBadgeDao.save(badge);
                    return new Response<>(true, "Added Sucsessfuly");
                } else {
                    System.out.println("badge not exists");

                }
            } else {
                System.out.println("season not exists");

            }
        } else {
            System.out.println("user not exists");
        }
        return new Response<>(false, "Error Ocure");

    }

}