/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.Season;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ashraf_R
 */
@Repository
public interface SeasonDao extends CrudRepository<Season,Integer> {
    @Query(name = "UserSeason.findBySeasonId")
    public Season getSeasonUsers(@Param("seasonId") Integer seasonId);
    
    @Query(value ="select MAX(s.seasonId) from Season s")
    public Integer getCurrentSeason();
}
