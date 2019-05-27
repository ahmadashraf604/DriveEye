package com.mycompany.controller;

import com.mycompany.bean.League;
import com.mycompany.bean.User;
import com.mycompany.dao.LeagueDao;
import com.mycompany.dto.LeagueDto;
import com.mycompany.utill.Response;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("league")
public class LeagueController {

    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    LeagueDao leagueDao;

    @Autowired
    UserController userController;

    @Autowired
    UserLeagueContoller userLeagueContoller;

    @PostMapping("add")
    public Response<?> addLeague(@Param("name") String name, @Param("ownerId") int ownerId) {
        User user = userController.existUserById(ownerId);
        if (user != null) {
            League league = new League();
            league.setName(name);
            league.setCode(getRandomCode(6));
            league.setOwnerId(user);
            League savedLeague = leagueDao.save(league);
            if (savedLeague != null) {
                userLeagueContoller.subscribe(savedLeague.getCode(), ownerId);
                return new Response<>(true, convertLeagueToDto(savedLeague));
            }
            return new Response<>(false, "canot save league");
        } else {
            return new Response<>(false, "user isn't found");
        }
    }

    @GetMapping("getAll")
    public Response<?> getAllLeague() {
        Iterable<League> leagues = leagueDao.findAll();
        List<LeagueDto> leagueDtos = new ArrayList<>();
        if (leagues.iterator().hasNext()) {
            for (League league : leagues) {
                leagueDtos.add(convertLeagueToDto(league));
            }
            return new Response<>(true, leagueDtos);
        } else {
            return new Response<>(false, "no leagues found");
        }
    }

    @DeleteMapping("delete/{leagueId}")
    public Response<?> deleteUserLeage(@PathVariable int leagueId) {
        League league = isLeagueExisted(leagueId);
        if (league != null) {
            leagueDao.delete(league);
            return new Response<>(true, "delete successfully");
        }
        return new Response<>(false, "no such league");
    }

    private LeagueDto convertLeagueToDto(League savedLeague) {
        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setCode(savedLeague.getCode());
        leagueDto.setLeagueId(savedLeague.getLeagueId());
        leagueDto.setName(savedLeague.getName());
        leagueDto.setOwnerId(savedLeague.getOwnerId().getUserId());
        leagueDto.setName(savedLeague.getName());
        leagueDto.setScore(0);
        leagueDto.setRank(1);
        return leagueDto;
    }

    // generate random key for league code 
    public String getRandomCode(long count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public League isLeagueExisted(String code) {
        return leagueDao.findLeagueByCode(code);
    }

    public League isLeagueExisted(int leagueID) {
        if (leagueDao.existsById(leagueID)) {
            return leagueDao.findById(leagueID).get();
        }
        return null;
    }
}
