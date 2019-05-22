/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Badge;
import com.mycompany.bean.Car;
import com.mycompany.dao.BadgeDao;
import com.mycompany.utill.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sallam
 */
@RestController
@RequestMapping("/badge")
public class BadgeController {

    @Autowired
    private BadgeDao badgeDao;

    public void setBadgeDao(BadgeDao badgeDao) {
        this.badgeDao = badgeDao;
    }

    @PostMapping("/add")
    public Response<?> addBadge(@Param("name") String name, @Param("type") String type) {
        Badge badge = new Badge();
//        badge.setImage(null);
        badge.setName(name);
        badge.setType(type);
        badgeDao.save(badge);

        return new Response<>(true, "badge added successfully");
    }

    @GetMapping("/getAll")
    public Response<?> getAllBadges() {
        Iterable<Badge> badges = badgeDao.findAll();
        if (badges.iterator().hasNext()) {
            return new Response<>(true, badges);
        } else {
            return new Response<>(false, "badges not found");
        }
    }

}
