package com.epam.rest;

import com.epam.ServiceApi;
import com.epam.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addStudent(@RequestBody Student student) {
        LOGGER.debug("rest: addStudent({})", student);
        return service.addStudent(student);
    }

    @GetMapping(value = "/students/count")
    public @ResponseBody
    Integer getStudentsCount(@RequestParam(value = "groupId", required = false) Integer groupId) {
        LOGGER.debug("rest: getStudentsCount({})", groupId);
        return service.getStudentsCount(groupId);
    }
}
