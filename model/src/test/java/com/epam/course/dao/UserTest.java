package com.epam.course.dao;

import com.epam.course.model.User;
import org.junit.Assert;

public class UserTest {

    @org.junit.Test
    public void getUserId() throws Exception {
        User user = new User();
        user.setUserId(11);
        Assert.assertEquals("User id: ", (Integer)11, (Integer)user.getUserId());
    }

    @org.junit.Test
    public void getLogin() throws Exception {
        User user = new User();
        user.setLogin("qwerty");
        Assert.assertEquals("User login: ", "qwerty", user.getLogin());
    }

    @org.junit.Test
    public void getPassword() throws Exception {
        User user = new User();
        user.setDescription("user for test");
        Assert.assertEquals("User desc.: ", "user for test", user.getDescription());
    }

    @org.junit.Test
    public void getDesctription() throws Exception {
        User user = new User();
        user.setPassword("123456");
        Assert.assertEquals("User password: ", "123456", user.getPassword());
    }

}