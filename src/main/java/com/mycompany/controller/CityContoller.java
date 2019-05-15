/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Car;
import com.mycompany.bean.City;
import com.mycompany.dao.CityDao;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
