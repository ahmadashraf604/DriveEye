/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.City;
import com.mycompany.dao.CityDao;
import com.mycompany.utill.Response;
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
@RequestMapping("city")
public class CityContoller {

    @Autowired
    CityDao cityDao;

    @GetMapping("add")
    public Response<?> add(@Param String name) {
        City c = new City();
        c.setName(name);
        cityDao.save(c);
        return new Response<>(true, "add sucessfully");

    }

    @GetMapping("getAll")
    public Response<?> getAll() {
        Iterable<City> citys = cityDao.findAll();
        if (citys.iterator().hasNext()) {
            return new Response<>(true, citys);
        } else {
            return new Response<>(false, "no cities found ");
        }
    }

    @GetMapping("get/{cityId}")
    public Response<?> getUsersByCityId(@PathVariable int cityId) {
        City city = findCityById(cityId);
        if (city != null) {
            return new Response<>(true, city.getUserCollection());
        }
        return new Response<>(false, "no city have this id");

    }

    public City findCityById(int cityId) {
        if (cityDao.existsById(cityId)) {
            return cityDao.findById(cityId).get();
        }
        return null;
    }

}
