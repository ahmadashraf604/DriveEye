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
    @Query(value = "update UserSeason userSeason set userSeason.score = (select u.score from UserSeason u where userSeason.user = u.user AND u.season = (SELECT MAX(s) FROM Season s)) + :score where userSeason.user = :user AND userSeason.season = (SELECT MAX(sl) FROM Season sl)")
    public void increaseScore(@Param("score") Integer score, @Param("user") User user);

    @Query(name = "UserSeason.getUserSeasonScore")
    public List<UserSeason> getScore(@Param("userId") Integer userId);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(value = "delete from UserSeason u where u.userSeasonPK.userId = :userId and u.userSeasonPK.seasonId = :seasonId")
    public void deleteUserSeason(@Param("userId") Integer userId, @Param("seasonId") Integer seasonId);
}
