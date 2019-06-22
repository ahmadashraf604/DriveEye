/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.Car;
import com.mycompany.bean.User;
import com.mycompany.dao.CarDao;
import com.mycompany.dao.UserDao;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.RequestEntity.method;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private UserDao userDao;

//   @Autowired
//   private CarService carService;
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
    public Response<?> getCars() {
        Iterable<Car> cars = carDao.findAll();
        if (cars.iterator().hasNext()) {
            return new Response<>(true, cars);

        } else {
            return new Response<>(false, "cars not found");
        }

    }

    @Transactional
    @GetMapping("/add/{id}")
    public Response<?> addCar(@PathVariable Integer id, @Param String brand, @Param String model) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        if (userDao.existsById(id)) {
            Car savedCar = carDao.save(car);
            System.out.println(savedCar.getCarId());
            
//            User oldUser = userDao.findById(id).get();
//            
//            oldUser.setCarId(savedCar);
//            System.out.println(oldUser.getPassword());
//            userDao.save(oldUser);
            userDao.updateUserCarId(id, savedCar);
            
            return new Response<>(true, "car added and user updated correctly");
        } else {
            return new Response<>(true, "something went wrong");

        }

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
//    @RequestMapping(value="/edit", method= RequestMethod.PUT)
//    public Response <?> editCar( @RequestBody Car car ,@PathVariable Integer id ) {        
//        car.setBrand(car.getBrand());
//        car.setModel(car.getModel());
//        
//        carDao.save(car);
//       
//       return new Response<>(true,"car added sucessfully");
//        
//     
//    }
//    public void updateCar(Integer id, Car car) {
//        
//        Car c = new Car();
//        
//            CarController cController = new CarController();
//            List<Car> cars = new ArrayList<>();
//            cars = (List<Car>) cController.getCars();
//            for(int i = 0; i<cars.size();i++){
//                c = cars.get(i);
//                System.out.println("ghghghgh"+cars.get(1));
//                if(c.getCarId().equals(id)){
//                    cars.set(i,c);
//                    return;
//                }
//            }
//    }
//    
//    @RequestMapping(value="/edit", method= RequestMethod.PUT)
//    public void updateCar(@RequestBody Car car , @PathVariable Integer id){
//        updateCar(id, car);
//    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    //@GetMapping(value= "edit/{id}")
    public Response<?> updateCar(@Param Integer id, @Param String brand, @Param String model) {
        Car car = new Car();
        Optional<Car> c = carDao.findById(id);
//        if(car.getCarId().equals(id)){
        car.getBrand();
        car.getModel();
        car.setBrand(car.getBrand());
        car.setModel(car.getModel());
        car.setReleaseyear(car.getReleaseyear());
        carDao.updateCar(brand, model, id);

        return new Response<>(true, "car updated sucessfully");
    }

}
