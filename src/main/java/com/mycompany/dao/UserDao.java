/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.User;
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
    User findUserByEmail(@Param("email") String email,@Param("password") String password);
    
}
