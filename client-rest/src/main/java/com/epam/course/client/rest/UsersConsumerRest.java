package com.epam.course.client.rest;

import com.epam.course.client.rest.api.UsersConsumer;
import com.epam.course.client.exception.ServerDataAccessException;
import com.epam.course.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * User consumer REST implementation.
 */
public class UsersConsumerRest implements UsersConsumer {

    private String hostUrl;
    private String urlUsers;
    private String urlUser;

    RestTemplate restTemplate;

    public UsersConsumerRest(String hostUrl, String urlUsers, String urlUser) {
        this.hostUrl = hostUrl;
        this.urlUsers = urlUsers;
        this.urlUser = urlUser;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(hostUrl + "/" + urlUsers, List.class);
        List<User> users = (List<User>) responseEntity.getBody();
        return users;
    }

    @Override
    public User getUserById(Integer userId) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(hostUrl + "/" + urlUser + "/id" + userId, User.class);
        Object user = responseEntity.getBody();
        return (User) user;
    }

    @Override
    public User getUserByLogin(String userLogin) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(hostUrl + "/" + urlUser + "/" + userLogin, User.class);
        Object user = responseEntity.getBody();
        return (User) user;
    }

    @Override
    public Integer addUser(User user) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.postForEntity(hostUrl + "/" + urlUser , user, Integer.class);
        Object id = responseEntity.getBody();
        return (Integer) id;
    }

    @Override
    public void updateUser(User user) throws ServerDataAccessException {
        restTemplate.exchange(hostUrl + "/" + urlUser + "/" + user.getUserId()
                        + "/" + user.getLogin() + "/" + user.getPassword() + "/" + user.getDescription()
                        , HttpMethod.PUT, null, User.class);
    }

    @Override
    public void deleteUser(Integer userId) throws ServerDataAccessException {
        restTemplate.delete(hostUrl + "/" + urlUser + "/" + userId);
    }
}