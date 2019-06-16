/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.User;
import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserLeaguePK;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Abdelrahman
 */
@Repository
public interface UserLeagueDao extends CrudRepository<UserLeague, UserLeaguePK> {

    @Query(name = "UserLeague.findByUserIdAndLeagueId")
    UserLeague findUserLeague(@Param("userId") int userId, @Param("leagueId") int leagueId);

    @Query(name = "UserLeague.findByUserId")
    public List<UserLeague> getSubscribedLeague(@Param("userId") int userID);

    @Query(name = "UserLeague.findByLeagueId")
    public List<UserLeague> getUsers(@Param("leagueId") int leagueId);

    @Query(value = "select l.row_number "
            + "from (select *, ROW_NUMBER() OVER(order by score DESC) from user_league "
            + "where league_id = :leagueId "
            + "order by score DESC) l "
            + "where user_id = :userId",
            nativeQuery = true)
    public Integer getRank(@Param("userId") int userID, @Param("leagueId") int leagueId);

    @Transactional
    @Modifying
    @Query(value = "update UserLeague ul set ul.score = (select u.score from UserLeague u where u.user = ul.user AND u.league = ul.league ) + :score where ul.user = :user")
    public void increaseScore(@Param("score") Integer score, @Param("user") User user);
}
