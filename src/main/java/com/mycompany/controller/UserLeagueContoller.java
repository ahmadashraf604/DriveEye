package com.mycompany.controller;

import com.mycompany.bean.League;
import com.mycompany.bean.User;
import com.mycompany.bean.UserLeague;
import com.mycompany.bean.UserLeaguePK;
import com.mycompany.dao.UserLeagueDao;
import com.mycompany.dto.LeagueDto;
import com.mycompany.dto.UserLeagueDto;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userLeague")
public class UserLeagueContoller {

    @Autowired
    UserLeagueDao userLeagueDao;

    @Autowired
    LeagueController leagueController;

    @Autowired
    UserController userController;

    @Transactional
    @PostMapping("join/{userID}")
    public Response<?> subscribe(@Param("leagueCode") String leagueCode, @PathVariable int userID) {
        User user = userController.existUserById(userID);
        if (user != null) {
            League league = leagueController.isLeagueExisted(leagueCode);
            if (league != null) {
                if (!isSubscribed(league.getLeagueId(), userID)) {
                    UserLeague userLeague = new UserLeague();
                    userLeague.setLeague(league);
                    userLeague.setUser(user);
                    userLeague.setScore(0);
                    UserLeaguePK userLeaguePK = new UserLeaguePK();
                    userLeaguePK.setLeagueId(league.getLeagueId());
                    userLeaguePK.setUserId(userID);
                    userLeague.setUserLeaguePK(userLeaguePK);
                    userLeagueDao.save(userLeague);
                    return new Response<>(true, "add sucessfully");
                } else {
                    return new Response<>(false, "user aready subscribed");
                }
            } else {
                return new Response<>(false, "no such league with code: " + leagueCode);
            }
        } else {
            return new Response<>(false, "no such user with this id: " + userID);
        }
    }

    @DeleteMapping("delete/{LeagueID}/{userID}")
    public Response<?> deleteUserLeage(@PathVariable int LeagueID, @PathVariable int userID) {
        UserLeaguePK userLeaguePK = new UserLeaguePK();
        userLeaguePK.setLeagueId(LeagueID);
        userLeaguePK.setUserId(userID);
        if (userLeagueDao.existsById(userLeaguePK)) {
            UserLeague userLeague = userLeagueDao.findById(userLeaguePK).get();
            if (userLeague != null) {
                userLeagueDao.delete(userLeague);
                return new Response<>(true, "delete successfully");
            }
        }
        return new Response<>(false, "no such league with user");
    }

    @Transactional
    @GetMapping("subscribed/{userID}")
    public Response<?> getSubscrubesLeague(@PathVariable int userID) {
        List<UserLeague> userLeagues = userLeagueDao.getSubscribedLeague(userID);
        if (userLeagues.size() > 0) {
            List<LeagueDto> leagueDtos = new ArrayList<>();
            for (UserLeague userLeague : userLeagues) {
                leagueDtos.add(convertLeagueToDto(userLeague, userID));
            }
            return new Response<>(true, leagueDtos);
        }
        return new Response<>(false, "no subscribed league");
    }
    
    @Transactional
    @GetMapping("getUsers/{leagueID}")
    public Response<?> getUsers(@PathVariable int leagueID) {
        League league = leagueController.isLeagueExisted(leagueID);
        if(league != null){
            List<UserLeague> userLeagues = userLeagueDao.getUsers(leagueID);
            List<UserLeagueDto> userLeagueDtos = new ArrayList<>();
            for(UserLeague userLeague: userLeagues){
                UserLeagueDto userLeagueDto = new UserLeagueDto();
                userLeagueDto.setScore(userLeague.getScore());
                userLeagueDto.setUsername(userLeague.getUser().getFirstName()+" "+userLeague.getUser().getLastName());
                userLeagueDto.setUserID(userLeague.getUser().getUserId());
                userLeagueDto.setRank(userLeagueDao.getRank(userLeague.getUser().getUserId(), leagueID));
                userLeagueDtos.add(userLeagueDto);
            }
            return new Response<>(true, userLeagueDtos);
        }
        return new Response<>(false, "no such league");
    }

    // check league if  user was subscribed in this league
    public boolean isSubscribed(int leagueId, int userId) {
        return userLeagueDao.findUserLeague(userId, leagueId) != null;
    }

    // truncate all leaguesfrom db  in the end of the season 
    public void seleteAll() {
        userLeagueDao.deleteAll();
    }

    private LeagueDto convertLeagueToDto(UserLeague userLeague, int userID) {
        League league = userLeague.getLeague();
        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setCode(league.getCode());
        leagueDto.setLeagueId(league.getLeagueId());
        leagueDto.setName(league.getName());
        leagueDto.setOwnerId(league.getOwnerId().getUserId());
        leagueDto.setScore(userLeague.getScore());
        leagueDto.setRank(userLeagueDao.getRank(userID, league.getLeagueId()));
        return leagueDto;
    }
    
    public void increaseScore(User user, int score){
        userLeagueDao.increaseScore(score, user);
    }
}
