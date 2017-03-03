package com.epam.course.service;

import com.epam.course.model.User;
import com.epam.course.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());

    @Autowired
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        LOGGER.debug("getAllUsers()");

        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Integer userId) throws DataAccessException {
        LOGGER.debug("getAllUsers({})", userId);

        Assert.notNull(userId, "User ID should not be null.");
        //TODO: check user ID
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String userLogin) throws DataAccessException {
        LOGGER.debug("getUserByLogin({})", userLogin);

        Assert.hasText(userLogin, "User login should not be null.");
        //TODO: check login

        return userDao.getUserByLogin(userLogin);
    }

    @Override
    public Integer addUser(User user) throws DataAccessException {
        LOGGER.debug("addUser({})", user.getUserId());

        Assert.notNull(user, "User should not be null.");
        Assert.isNull(user.getUserId(), "User ID should be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");

        User existentUser = getUserByLogin(user.getLogin());

        Assert.isNull(existentUser, "User with login \"" + user.getLogin() + "\" already exist");

        //TODO: check new user
        return userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) throws DataAccessException {
        LOGGER.debug("updateUser({})", user.getUserId());

        Assert.notNull(user, "User should not be null.");
        Assert.notNull(user.getUserId(), "User ID should not be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");
        //TODO: check update user
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userId) throws DataAccessException {
        LOGGER.debug("deleteUser({})", userId);

        Assert.notNull(userId);
        //TODO: check user ID
        userDao.deleteUser(userId);
    }
}
