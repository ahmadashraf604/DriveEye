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
    public Response<?> add(@Param String title, @Param String description) {
        if (description == null || title == null || description.trim().isEmpty() || title.trim().isEmpty()) {
            System.out.println("add");
            return new Response<>(true, "missing some data 'title or description' ");
        }
        Coaching coaching = new Coaching();
        coaching.setDescription(description);
        coaching.setTitle(title);
        coaching = coachingDao.save(coaching);
        if (coaching != null) {
            return new Response<>(true, "add  successfully");
        }

        return new Response<>(true, "some thing went wrong");
    }

    @GetMapping("get/{id}")
    public Response<?> getById(@PathVariable int id) {
        if (coachingDao.existsById(id)) {
            Coaching coaching = coachingDao.findById(id).get();
            if (coaching != null) {
                return new Response<>(true, coaching);
            }
        }
        return new Response<>(true, "coach not found ");
    }

    // return all coaches 
    @GetMapping("getAll")
    public Response<?> getAllLeague() {
        Iterable<Coaching> coachings = coachingDao.findAll();
        if (coachings.iterator().hasNext()) {
            return new Response<>(true, coachings);

        } else {
            return new Response<>(false, "no coaching found ");
        }
    }

    // delete coach by ID 
    @GetMapping("delete/{id}")
    public Response<?> deleteCoach(@PathVariable int id) {

        if (coachingDao.existsById(id)) {
            coachingDao.deleteById(id);
            return new Response<>(true, "coach deleted successfully");
        }
        return new Response<>(false, Constants.WRONG_MESSAGE);
    }

    @GetMapping("deleteAll")
    public Response<?> deleteAll() {
        coachingDao.deleteAll();
        Iterable<Coaching> coachings = coachingDao.findAll();
        if (coachings == null) {
            return new Response<>(true, "operation did not completed");
        }
        return new Response<>(true, "all coaches deleted");
    }
}
