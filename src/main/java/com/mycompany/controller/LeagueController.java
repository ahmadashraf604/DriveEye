
package com.mycompany.controller;

import com.mycompany.bean.League;
import com.mycompany.bean.User;
import com.mycompany.dao.LeagueDao;
import com.mycompany.dao.UserDao;
import com.mycompany.utill.Response;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("league")
public class LeagueController {

    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    LeagueDao leagueDao;
    @Autowired
    UserDao userDao;

    @GetMapping("create")
    public Response<?> createNewLege(@Param String name) {
        League league = new League();
        league.setName(name);
        league.setCode(getRandomCode(6));
        User user = userDao.findById(1).get();
        league.setOwnerId(user);
        leagueDao.save(league);
        return new Response<>(true, " league created successeully with code " + league.getCode());
    }

    @GetMapping("get/{id}")
    public Response<?> getAllLeague(@PathVariable int id ) {
        Iterable<League> leagues = leagueDao.findAll();
        if (leagues.iterator().hasNext()) {   
            return new Response<>(true, leagues);

        } else {
            return new Response<>(false, "no cities found ");
        }
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
}
