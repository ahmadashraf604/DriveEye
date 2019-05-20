package com.mycompany.controller;

import com.mycompany.bean.City;
import com.mycompany.dao.CityDao;
import com.mycompany.utill.Response;
import org.springframework.data.repository.query.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Constants;

@RestController
@RequestMapping("city")
public class CityContoller {

    @Autowired
    CityDao cityDao;

    @PostMapping("add")
    public Response<?> add(@Param("name") String name) {
        System.out.println("name  : " + name);
        if (!(name == null || name.trim().isEmpty())) {
            City c = new City();
            c.setName(name);
            cityDao.save(c);
            return new Response<>(true, "add sucessfully");
        } else {
            return new Response<>(true, "some thing went  wrong");
        }

    }

    @GetMapping("get")
    public Response<?> getAllCities() {
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

    @GetMapping("delete/{id}")
    public Response<?> deleteCoach(@PathVariable int id) {

        if (cityDao.existsById(id)) {
            cityDao.deleteById(id);
            return new Response<>(true, "city deleted successfully");
        }
        return new Response<>(false, Constants.WRONG_MESSAGE);

    }

}
