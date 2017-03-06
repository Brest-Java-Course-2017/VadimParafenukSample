package com.epam.rest;

import com.epam.ServiceApi;
import com.epam.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsGroupsController {

    private static final Logger LOGGER = LogManager.getLogger(StudentsGroupsController.class.getName());

    @Autowired
    ServiceApi service;

    @GetMapping(value = "/students")
    public @ResponseBody
    List<Student> getStudents(@RequestParam(value = "minGpa", required = false) Float minGpa,
                              @RequestParam(value = "maxGpa", required = false) Float maxGpa,
                              @RequestParam(value = "groupId", required = false) Integer groupId) {
        LOGGER.debug("rest: getStudents({}, {}, {})", minGpa, maxGpa, groupId);

        return service.getStudents(minGpa, maxGpa, groupId);
    }

    @GetMapping(value = "/students/id{id}")
    public @ResponseBody
    Student getStudentById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("rest: getStudentById({})", id);

        return service.getStudentById(id);
    }
}
