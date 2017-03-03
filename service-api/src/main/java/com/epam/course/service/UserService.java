package com.epam.course.service;

import com.epam.course.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * User service interface.
 */
public interface UserService {

    /**
     * Get all users list.
     *
     * @return all users list
     */
    List<User> getAllUsers() throws DataAccessException;

    /**
     * Get user by Id.
     *
     * @param userId user identifier.
     * @return user.
     */
    User getUserById(Integer userId) throws DataAccessException;

    /**
     * Get user by login.
     *
     * @param userLogin user login.
     * @return user
     * @throws DataAccessException
     */
    User getUserByLogin(String userLogin) throws DataAccessException;

    /**
     * Create new user.
     *
     * @param user user.
     * @return new user Id.
     */
    Integer addUser(User user) throws DataAccessException;

    /**
     * Update user.
     *
     * @param user user.
     */
    void updateUser(User user) throws DataAccessException;

    /**
     * Delete user.
     *
     * @param userId user.
     */
    void deleteUser(Integer userId) throws DataAccessException;

}
