/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.UserSeasonBadge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abdo Amin
 */
@Repository
public interface UserSeasonBadgeDao extends CrudRepository<UserSeasonBadge, Integer> {

    @Query(name = "UserSeasonBadge.findByUserId")
    public Iterable<UserSeasonBadge> findByUserId(
            @Param("userId") int userId
    );

    @Query(name = "UserSeasonBadge.findByUserSeasonBadgeId")
    public Iterable<UserSeasonBadge> findByUserIdAndSeasonId(
            @Param("userId") int userId,
            @Param("seasonId") int seasonId
    );
}
