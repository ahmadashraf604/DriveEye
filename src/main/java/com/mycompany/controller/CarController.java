/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Car;
import com.mycompany.bean.User;
import com.mycompany.dao.CarDao;
import com.mycompany.utill.Response;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tahoon
 */
@RestController
@RequestMapping("/car")
public class CarController {
    
   @Autowired
   private CarDao carDao;

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
   
    @GetMapping("/{id}")
    public Response<?> getcar(@PathVariable int id) {
        if (carDao.existsById(id)) {
            return new Response<>(true, carDao.findById(id).get());
        } else {
            return new Response<>(false, "car not found");
        }
    }
   

    @GetMapping("/get")
    public Response <?> getCars() {
        Iterable<Car> cars = carDao.findAll();
        if (cars.iterator().hasNext()) {
            return new Response<>(true, cars);
        }else{
            return new Response<>(false, "cars not found");
        } 
    }
    
    @GetMapping("/add")
    public Response <?> addCar(@Param String brand ,@Param String model) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        carDao.save(car);       
       return new Response<>(true,"car added sucessfully");
               
    }
    
//    @GetMapping("/edit")
//    public Response <?> editCar(@Param Integer id ) {
//        Car car = new Car();
//        if (carDao.findById(id).isPresent()){
//            Car existingCar = carDao.findById(id).get();
//
//            existingCar.setBrand(car.getBrand());
//            existingCar.setModel(car.getModel());
//
//            Car updatedCar = carDao.save(existingCar);
//
//            return new Response<>(true,"car has updated");
//            
//        }else{
//            return new Response<>(false,"car doesn't exist");
//            
//        }
//     
//    }
    
//    @GetMapping("/edit")
//    public Response <?> editCar(@Param Integer id ) {
//        Car car = new Car();
//        car.setBrand(car.getBrand());
//        car.setModel(car.getModel());
//        carDao.save(car);
//       
//       return new Response<>(true,"car added sucessfully");
//        
//     
//    }
}