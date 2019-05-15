/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ashraf_R
 */
@Repository
public interface CarDao extends CrudRepository<Car,Integer> {
       @Query(value = "update Car c set c.brand = :brand,c.model = :model  where id = :carId")
    public Car updateCar(Car car);
    
}
