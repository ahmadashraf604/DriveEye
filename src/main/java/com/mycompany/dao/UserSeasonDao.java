/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserSeason;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tahoon
 */
@Repository
public interface UserSeasonDao extends CrudRepository<UserSeason, Integer> { 
   
    @Query(name = "UserSeason.findByUserId")
    public List<UserSeason> getUserSeasons(@Param("userId") Integer userId);
    
    @Query(name = "UserSeason.findBySeasonIdAndUserId")
    public List<UserSeason> getUserScore(@Param("userId") Integer userId , @Param("seasonId") Integer seasonId );
    

}