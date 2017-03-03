package com.epam.course.dao;

import com.epam.course.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class UserDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class.getName());

    @Autowired
    UserDao userDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.error("execute: setUpBeforeClass()");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.error("execute: tearDownAfterClass()");
    }

    @Before
    public void beforeTest() {
        LOGGER.error("execute: beforeTest()");
    }

    @After
    public void afterTest() {
        LOGGER.error("execute: afterTest()");
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> users = userDao.getAllUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = userDao.getUserById(1);
        assertNotNull(user);
        assertEquals("userLogin1", user.getLogin());
    }

    @Test
    public void getUserByLoginTest() throws Exception {
        User user = userDao.getUserByLogin("userLogin1");

        assertNotNull(user);
        assertEquals(user.getPassword(), "userPassword1");
    }

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: addUserTest()");

        User user = new User("userLogin3", "userPassowrd3", "user for test");
        Integer newUserId = userDao.addUser(user);

        user = userDao.getUserById(newUserId);

        assertNotNull(user);
        assertEquals("user for test", user.getDescription());

        userDao.deleteUser(newUserId);
    }

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void testAddDuplicateUser() throws Exception {
        LOGGER.debug("test: testAddDuplicateUser()");

        User xUser = new User(1, "userLogin1", "userPassword1", "userDesck1");

        userDao.addUser(xUser);
    }

    @Test
    public void updateUserTest() throws Exception {

        LOGGER.debug("test: updateUserTest()");

        User originalUser = userDao.getUserById(2);
        User user = new User(2, "newUser2", "newPass2", "newDesc2");

        userDao.updateUser(user);

        user = userDao.getUserById(2);

        assertNotNull(user);
        assertEquals((Integer) 2, user.getUserId());
        assertEquals("newDesc2", user.getDescription());

        userDao.updateUser(originalUser);
        user = userDao.getUserById(2);

        assertNotNull(user);
        assertEquals((Integer) 2, user.getUserId());
        assertEquals(originalUser.getDescription(), user.getDescription());
    }

    @Test
    public void deleteUserTest() throws Exception {
        LOGGER.debug("test: deleteUserTest()");

        User user = new User("newUser3", "newPass3", "newDesc3");
        Integer newUserId = userDao.addUser(user);
        userDao.deleteUser(newUserId);

        user = userDao.getUserById(newUserId);
        assertNull(user);
    }
}