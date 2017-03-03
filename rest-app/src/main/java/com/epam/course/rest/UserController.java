package com.epam.course.rest;

import com.epam.course.model.User;
import com.epam.course.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUsers() {
       LOGGER.debug("getUsers()");

       return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer createUser(@RequestBody User user) {
        LOGGER.debug("addUser({})", user);

        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable("id") Integer id) {
        LOGGER.debug("deleteUser({})", id);

        userService.deleteUser(id);
    }

    @RequestMapping(value = "/user/{id}/{login}/{password}/{desc}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable(value = "id") int id,
                           @PathVariable(value = "desc") String desc,
                           @PathVariable(value = "login") String login,
                           @PathVariable(value = "password") String password) {
        LOGGER.debug("updateUser(id: {}, login: {}, password: {}, desc: {})", id, login, password, desc);

        userService.updateUser(new User(id, login, password, desc));
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public @ResponseBody User getUser(@PathVariable(value = "login") String login) {
        LOGGER.debug("getUser: login = {}", login);

        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/user/id{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public @ResponseBody User getUserById(@PathVariable("id") Integer id) {
        LOGGER.debug("getUserById: login = {}", id);

        return userService.getUserById(id);
    }
}