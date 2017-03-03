package com.epam.course.client.rest;

import com.epam.course.client.rest.api.UsersConsumer;
import com.epam.course.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UsersConsumerTest {

    @Autowired
    UsersConsumer usersConsumer;

    private static final Logger LOGGER = LogManager.getLogger(UsersConsumerTest.class.getName());

    @Value("${user.protocol}://${user.host}:${user.port}/")
    private String hostUrl;

    @Value("${point.users}")
    private String urlUsers;

    @Value("${point.user}")
    private String urlUser;

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> users = usersConsumer.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void getUserByLoginTest() throws Exception {
        User user = usersConsumer.getUserByLogin("userLogin1");
        assertEquals(user.getPassword(), "userPassword1");
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = usersConsumer.getUserById(1);
        assertEquals(user.getLogin(), "userLogin1");
    }

    @Test
    public void addUserAndDeleteUserTest() throws Exception {
        User newUser = new User("newUser", "qwerty", "newUserDesc");
        Integer id = usersConsumer.addUser(newUser);

        newUser = usersConsumer.getUserById(id);
        assertEquals(newUser.getLogin(), "newUser");

        usersConsumer.deleteUser(id);
        newUser = usersConsumer.getUserById(id);
        assertNull(newUser);

    }

    @Test
    public void updateUserTest() throws Exception {
        User originalUser = usersConsumer.getUserById(1);
        User updatedUser = new User(1,"updatedUser", "somePass", "someDesc");

        usersConsumer.updateUser(updatedUser);
        updatedUser = usersConsumer.getUserById(1);
        assertEquals("updatedUser", updatedUser.getLogin());

        usersConsumer.updateUser(originalUser);
        updatedUser = usersConsumer.getUserById(1);
        assertEquals(updatedUser, originalUser);
    }

}
