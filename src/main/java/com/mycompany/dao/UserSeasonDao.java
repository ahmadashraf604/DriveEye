/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.User;
import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserSeason;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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
    public List<UserSeason> getUserScore(@Param("userId") Integer userId, @Param("seasonId") Integer seasonId);

    @Query(name = "UserSeason.findBySeasonIdWithOrder")
    public List<UserSeason> getSeasonScoreOrderd(@Param("seasonId") Integer seasonId);

    @Transactional
    @Modifying
    @Query(value = "update UserSeason ul set ul.score = (select u.score from UserSeason u where u.user = ul.user AND u.season = ul.season ) + :score where ul.user = :user")
    public void increaseScore(@Param("score") Integer score, @Param("user") User user);
}
