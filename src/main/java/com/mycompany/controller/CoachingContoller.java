package com.mycompany.controller;

import com.mycompany.bean.Coaching;
import com.mycompany.dao.CoachingDao;
import utils.Constants;
import com.mycompany.utill.Response;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coach")
public class CoachingContoller {

    @Autowired
    CoachingDao coachingDao;

    @GetMapping("add")
    public Response<?> add(@Param String description) {
        System.out.println("describtion : " + description);
        if (description == null || description.trim().isEmpty()) {
            return new Response<>(true, "invalid coach name");

        }
        Coaching coaching = new Coaching();

        coaching.setDescription(description);
        coaching = coachingDao.save(coaching);
        if (coaching != null) {
            return new Response<>(true, "add  successfully");

        }

        return new Response<>(true, "some thing went wrong");
    }

    @GetMapping("/{id}")
    public Response<?> getById(@PathVariable int id) {
        return new Response<>(true, coachingDao.findById(id).get());
    }

    @GetMapping("get")
    public Response<?> getAllLeague() {
        Iterable<Coaching> coachings = coachingDao.findAll();
        if (coachings.iterator().hasNext()) {
            return new Response<>(true, coachings);

        } else {
            return new Response<>(false, "no coaching found ");
        }
    }

    @GetMapping("delete/{id}")
    public Response<?> deleteCoach(@PathVariable int id) {
        System.out.println("caoching" + id);

        boolean coaching = coachingDao.existsById(id);
        System.out.println("caoching" + coaching);
        if (coaching) {

            coachingDao.deleteById(id);
            return new Response<>(true, "coach deleted successfully");

        }
        return new Response<>(false, Constants.WRONG_MESSAGE);

    }

}
