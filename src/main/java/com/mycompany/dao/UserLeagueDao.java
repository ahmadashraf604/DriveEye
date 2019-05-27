/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserLeaguePK;
import java.util.List;
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

    @Query(value = "select l.row_number "
            + "from (select u, ROW_NUMBER() OVER(order by u.score DESC) from user_league u"
            + "where u.league_id = :leagueId "
            + "order by u.score DESC) l "
            + "where l.user_id = :userId",
            nativeQuery = true)
    public Integer getRank(@Param("userId") int userID, @Param("leagueId") int leagueId);

}
