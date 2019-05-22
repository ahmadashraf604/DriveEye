package com.mycompany.controller;

import com.mycompany.bean.League;
import com.mycompany.bean.User;
import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserLeaguePK;
import com.mycompany.dao.UserLeagueDao;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user_league")
public class UserLeagueContoller {

    @Autowired
    UserLeagueDao userLeagueDao;

    @Autowired
    LeagueController leagueController;

    @Autowired
    UserController userController;

    @GetMapping("join")
    public Response<?> subscribe(@Param String leagueCode, @Param int userID) {
        User user = userController.existUserById(userID);
        if (user != null) {
            League league = leagueController.isLeagueExisted(leagueCode);
            if (league != null) {
                if (!isSubscribed(league.getLeagueId(), userID)) {
                    UserLeague userLeague = new UserLeague();
                    userLeague.setLeague(league);
                    userLeague.setUser(user);
                    UserLeaguePK userLeaguePK = new UserLeaguePK();
                    userLeaguePK.setLeagueId(league.getLeagueId());
                    userLeaguePK.setUserId(userID);
                    userLeague.setUserLeaguePK(userLeaguePK);
                    userLeagueDao.save(userLeague);
                    return new Response<>(true, "add sucessfully");
                } else {
                    return new Response<>(false, "user aready subscribed ");
                }
            } else {
                return new Response<>(false, "no such league with code : " + leagueCode);
            }
        } else {
            return new Response<>(false, "user is null  " + userID);
        }
    }

    @GetMapping("subscribed")
    public Response<?> getSubscrubesLeague(@Param int user_id) {
        List<UserLeague> leagues = userLeagueDao.getSubscribedLeague(user_id);
        if (leagues.size() > 0) {

            List<UserLeague> userLeagues = new ArrayList<>();
            for (UserLeague l : leagues) {
                UserLeague league = new UserLeague();
                league.setLeague(l.getLeague());
                league.setScore(l.getScore());
                league.setUserLeaguePK(l.getUserLeaguePK());
                userLeagues.add(league);
            }
            System.out.println("league size : " + leagues.size());
            return new Response<>(true, userLeagues);

        } else {
            return new Response<>(true, "now subsscribed league");
        }
    }

    // check league if  user was subscribed in this league
    public boolean isSubscribed(int leagueId, int userId) {
        return userLeagueDao.findUserLeague(userId, leagueId) != null;

    }

    // truncate all leaguesfrom db  in the end of the season 
    public void seleteAll() {
        userLeagueDao.deleteAll();
    }
}
