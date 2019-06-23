/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.Car;
import com.mycompany.bean.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ashraf_R
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

//    @Query(value = "SELECT u FROM User u where u.email = :email and u.password = :password")
    @Query(name = "User.login")
    User findUserByEmail(@Param("email") String email, @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(name = "User.updateCarInfo", nativeQuery = true)
    int updateUserCarId(@Param("userId") Integer userId, @Param("carId") Car carId);

//    @Transactional
//    @Modifying
//       @Query(value = "update User u set u.first_name = :firsNtame and,"
//               + "u.last_name = :lastName and,"
//               + "u.email = :email and,u.city = :city and,"
//               + "u.password = :password and,u.image = :image where user_id = :userId", nativeQuery = true)
//    void updateUser(@Param("firstName") String fName , @Param("lastName") String lName,
//            @Param("email") String email ,@Param("cityId") Integer cityId,@Param("image") Byte image,
//            @Param("password") String password , @Param("user_id") Integer userId);
//    
    @Transactional
    @Modifying
    @Query(value = "update User u set u.image = :image where u.id = :userId")
    public void updateImage(@Param("image") byte[] image, @Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.email = :email where u.id = :userId")
    public void updateEmail(@Param("email") String email, @Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.level = (select us.level from User us where us.userId = :userId ) + :level where u.userId = :userId")
    public void updateLeve(@Param("userId") Integer userId, @Param("level") Integer level);
}
