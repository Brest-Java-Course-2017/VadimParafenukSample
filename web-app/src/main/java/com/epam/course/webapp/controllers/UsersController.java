package com.epam.course.webapp.controllers;

import com.epam.course.model.User;
import com.epam.course.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UsersController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:users";
    }

    @GetMapping(value = "/users")
    public String users(Model model) {
        LOGGER.debug(" /users page.");
        List usersList = userService.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "users";
    }

    @GetMapping(value = "/user")
    public String editUser(@RequestParam("id") Integer id,
                           Model model) {
        LOGGER.debug("/user({})",id);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/createuser")
    public String addUser(Model model) {
        LOGGER.debug("show create user page");

        User user = new User();
        model.addAttribute("user", user);

        return "adduser";
    }

    @PostMapping(value = "/createuserpost")
    public String createUser(@ModelAttribute(value = "user") User user) {
        LOGGER.debug("create user " + user.getLogin());

        try {
            userService.addUser(user);

            return "redirect:/users";
        } catch (Exception e) {
            return "redirect:/errorpage?login=" + user.getLogin();
        }
    }

    @GetMapping(value = "/errorpage")
    public String showErrorPage(@RequestParam("login") String login, Model model) {
        LOGGER.debug("show error page (login: " + login + ")");

        model.addAttribute("login", login);
        return "createusererror";
    }

    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam("id") Integer id) {
        LOGGER.debug("delete user " + id.toString());

        userService.deleteUser(id);
        return "redirect:users";
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public String updateUser(@RequestParam("id") Integer id,
                             @RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("description") String description) {
        LOGGER.debug("updateUser");

        User user = userService.getUserById(id);

        if (user == null)
            return "redirect:users";

        user.setLogin(login);
        user.setPassword(password);
        user.setDescription(description);
        userService.updateUser(user);

        return "redirect:users";
    }
}
