/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.mycompany.bean.Badge;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSeasonBadge;
import com.mycompany.bean.UserSeasonBadgePK;
import com.mycompany.dao.BadgeDao;
import com.mycompany.dao.SeasonDao;
import com.mycompany.dao.UserDao;
import com.mycompany.dao.UserSeasonBadgeDao;
import com.mycompany.dto.BadgeDto;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @GetMapping("/{userId}")
    public Response<?> getUserBadgeInAllSeason(@PathVariable int userId) {
        Iterable<UserSeasonBadge> userSeasonBadges = userSeasonBadgeDao.findByUserId(userId);
        List<BadgeDto> badges = new ArrayList<>();
        if (userSeasonBadges.iterator().hasNext()) {
            for (UserSeasonBadge userSeasonBadge : userSeasonBadges) {
                BadgeDto badgeDto = new BadgeDto();
                badgeDto.setBadgeId(userSeasonBadge.getBadge().getBadgeId());
                badgeDto.setName(userSeasonBadge.getBadge().getName());
                badgeDto.setType(userSeasonBadge.getBadge().getType());
                badgeDto.setImage("/userBadge/image/" + userSeasonBadge.getBadge().getBadgeId());
                badges.add(badgeDto);
            }
            return new Response<>(true, badges);
        } else {
            return new Response<>(false, "user not have badge");
        }
    }

    @GetMapping("/image/{badgeId}")
    public ResponseEntity<ByteArrayResource> downloadBadgeImage(@PathVariable Integer badgeId) {
        // Load file from database
        Badge userSeasonBadge = badgeDao.findById(badgeId).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userSeasonBadge.getName() + "\"")
                .body(new ByteArrayResource(userSeasonBadge.getImage()));
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
