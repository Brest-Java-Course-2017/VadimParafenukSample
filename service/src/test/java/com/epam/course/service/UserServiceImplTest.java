package com.epam.course.service;

import com.epam.course.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImplTest.class.getName());

    @Autowired
    UserService userService;

    @Test
    public void getAllUsersTest() throws Exception {
        LOGGER.debug("test: getAllUsersTest()");

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        LOGGER.debug("test: getUserByIdTest()");

        User user = userService.getUserById(1);

        assertNotNull(user);
        assertEquals("userLogin1", user.getLogin());
    }

    @Test
    public void getUserByLoginTest() throws Exception {
        LOGGER.debug("test: getUserByLoginTest()");

        User user = userService.getUserByLogin("userLogin1");

        assertNotNull(user);
        assertEquals(user.getPassword(), "userPassword1");
    }

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: addUserTest()");

        User user = new User("userLogin3", "userPassowrd3", "user for test");
        Integer newUserId = userService.addUser(user);

        user = userService.getUserById(newUserId);

        assertNotNull(user);
        assertEquals("user for test", user.getDescription());

        userService.deleteUser(newUserId);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void addDuplicateUserTest() throws Exception {
        LOGGER.debug("test: addDuplicateUserTest()");

        User xUser = new User(1, "userLogin1", "userPassword1", "userDesck1");

        userService.addUser(xUser);
    }

    @Test
    public void updateUserTest() throws Exception {
        LOGGER.debug("test: updateUserTest()");

        User originalUser = userService.getUserById(2);
        User user = new User(2, "newUser2", "newPass2", "newDesc2");

        userService.updateUser(user);

        user = userService.getUserById(2);

        assertNotNull(user);
        assertEquals((Integer) 2, user.getUserId());
        assertEquals("newDesc2", user.getDescription());

        userService.updateUser(originalUser);
        user = userService.getUserById(2);

        assertNotNull(user);
        assertEquals((Integer) 2, user.getUserId());
        assertEquals(originalUser.getDescription(), user.getDescription());
    }

    @Test(expected = org.springframework.dao.DataAccessException.class)
    public void updateNonexistentUserTest() throws Exception {
        LOGGER.debug("test: updateNonexistentUserTest()");

        User user = new User(5, "someLogin", "somePass", "someDesc");
        userService.updateUser(user);
    }

    @Test
    public void deleteUserTest() throws Exception {
        LOGGER.debug("test: deleteUserTest()");

        User user = new User("newUser3", "newPass3", "newDesc3");
        Integer newUserId = userService.addUser(user);
        userService.deleteUser(newUserId);

        user = userService.getUserById(newUserId);
        assertNull(user);
    }

    @Test(expected = org.springframework.dao.DataAccessException.class)
    public void deleteNonexistentUserTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentUserTest()");

        userService.deleteUser(5);
    }
}