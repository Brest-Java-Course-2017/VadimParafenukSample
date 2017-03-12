package com.epam.rest;

import com.epam.Group;
import com.epam.ServiceApi;
import com.epam.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class StudentsGroupsController {

    private static final Logger LOGGER = LogManager.getLogger(StudentsGroupsController.class.getName());

    @Autowired
    ServiceApi service;

    // curl -v "localhost:8088/students/"
    // curl -v "localhost:8088/students?minGpa=5&maxGpa=9&groupId=1"
    @GetMapping(value = "/students")
    public
    @ResponseBody
    List<Student> getStudents(@RequestParam(value = "minGpa", required = false) Float minGpa,
                              @RequestParam(value = "maxGpa", required = false) Float maxGpa,
                              @RequestParam(value = "groupId", required = false) Integer groupId) {
        LOGGER.debug("rest: getStudents({}, {}, {})", minGpa, maxGpa, groupId);

        return service.getStudents(minGpa, maxGpa, groupId);
    }

    // curl -v "localhost:8088/students/id1"
    @GetMapping(value = "/students/id{id}")
    public
    @ResponseBody
    Student getStudentById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("rest: getStudentById({})", id);
        return service.getStudentById(id);
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"name":"NewStudent","gpa":4.2,"groupId":1}' -v "localhost:8088/students"
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Integer addStudent(@RequestBody Student student) {
        LOGGER.debug("rest: addStudent({})", student);
        return service.addStudent(student);
    }

    // curl -v "localhost:8088/students/count"
    @GetMapping(value = "/students/count")
    public
    @ResponseBody
    Integer getStudentsCount(@RequestParam(value = "groupId", required = false) Integer groupId) {
        LOGGER.debug("rest: getStudentsCount({})", groupId);
        return service.getStudentsCount(groupId);
    }

    // curl -v "localhost:8088/students/gpa"
    @GetMapping(value = "/students/gpa")
    public
    @ResponseBody
    Float getStudentsGpa(@RequestParam(value = "groupId", required = false) Integer groupId) {
        LOGGER.debug("rest: getStudentsGpa({})", groupId);
        return service.getStudentsGpa(groupId);
    }

    // curl -H "Content-Type: application/json" -X PUT -d '{"studentId":1,"name":"NewStudent","gpa":4.2,"groupId":1}' -v "localhost:8088/students"
    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStudent(@RequestBody Student student) {
        LOGGER.debug("rest: updateStudent({})", student);
        service.updateStudent(student);
    }

    // curl -X DELETE localhost:8088/students/id{id}
    @RequestMapping(value = "/students/id{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStudentById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("rest: deleteStudentById({})", id);
        service.deleteStudent(id);
    }

    // curl -v "localhost:8088/groups?minGradDate=2016-01-01&maxGradDate=2017-12-31"
    @GetMapping(value = "/groups")
    public
    @ResponseBody
    List<Group> getGroups(@RequestParam(value = "minGradDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minGradDate,
                          @RequestParam(value = "maxGradDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxGradDate) {
        LOGGER.debug("rest: getGroups({}, {})", minGradDate, maxGradDate);
        return service.getGroups(minGradDate, maxGradDate);
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"name":"II-13","graduationDate":"2019-05-31"}' -v "localhost:8088/groups"
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Integer addGroup(@RequestBody Group group) {
        LOGGER.debug("rest: addGroup({})", group);
        return service.addGroup(group);
    }

    // curl -H "Content-Type: application/json" -X PUT -d '{"groupId":1,"name":"II-13-UPT","graduationDate":"2222-05-31"}' -v "localhost:8088/groups"
    @RequestMapping(value = "/groups", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGroup(@RequestBody Group group) {
        LOGGER.debug("rest: updateGroup({})", group);
        service.updateGroup(group);
    }

    // curl -v "localhost:8088/groups/id1"
    @GetMapping(value = "/groups/id{id}")
    public
    @ResponseBody
    Group getGroupById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("rest: getGroupById({})", id);
        return service.getGroupById(id);
    }

    // curl -v "localhost:8088/groups/II-12"
    @GetMapping(value = "/groups/{name}")
    public
    @ResponseBody
    Group getGroupByName(@PathVariable(value = "name") String name) {
        LOGGER.debug("rest: getGroupByName({})", name);
        return service.getGroupByName(name);
    }

    // curl -X DELETE localhost:8088/group/id{id}
    @RequestMapping(value = "/groups/id{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteGroup(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("rest: deleteGroup({})", id);
        service.deleteGroup(id);
    }

    // curl -v "localhost:8088/groups/count"
    @GetMapping(value = "/groups/count")
    public
    @ResponseBody
    Integer getGroupsCount(@RequestParam(value = "minGradDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minGradDate,
                           @RequestParam(value = "maxGradDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxGradDate) {
        LOGGER.debug("rest: getGroupsCount({}, {})", minGradDate, maxGradDate);
        return service.getGroupsCount(minGradDate, maxGradDate);
    }
}