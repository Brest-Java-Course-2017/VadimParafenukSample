package com.epam.course.client.rest.api;

import com.epam.course.client.exception.ServerDataAccessException;
import com.epam.course.model.User;

import java.util.List;

/**
 * User consumer API.
 */
public interface UsersConsumer {

    /**
     * Get all users list.
     *
     * @return all users list
     */
    List<User> getAllUsers() throws ServerDataAccessException;

    /**
     * Get user by Id.
     *
     * @param userId user identifier.
     * @return user.
     */
    User getUserById(Integer userId) throws ServerDataAccessException;

    /**
     * Get user by login.
     *
     * @param userLogin user login.
     * @return user
     * @throws ServerDataAccessException
     */
    User getUserByLogin(String userLogin) throws ServerDataAccessException;

    /**
     * Create new user.
     *
     * @param user user.
     * @return new user Id.
     */
    Integer addUser(User user) throws ServerDataAccessException;

    /**
     * Update user.
     *
     * @param user user.
     */
    void updateUser(User user) throws ServerDataAccessException;

    /**
     * Delete user.
     *
     * @param userId user.
     */
    void deleteUser(Integer userId) throws ServerDataAccessException;

}
