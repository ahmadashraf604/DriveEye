/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Season;
import com.mycompany.dao.SeasonDao;
import com.mycompany.utill.Response;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tahoon
 */
@RestController
@RequestMapping("/season")
public class SeasonController {

    @Autowired
    private SeasonDao seasonDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<?> addSeason(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        Season season = new Season();
        season.setStartDate(startDate);
        season.setEndDate(endDate);
        seasonDao.save(season);
        return new Response<>(true, season);
        // return new Response<>(true, "Season added sucessfully");
    }
    
    public int getCurrentSeason(){
        return seasonDao.getCurrentSeason();
    }
}
