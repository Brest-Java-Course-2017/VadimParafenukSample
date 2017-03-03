package com.epam.course.dao;

import com.epam.course.model.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class.getName());

    @Value("${userDaoSql.getAllUsersSql}")
    String getAllUsersSql;

    @Value("${userDaoSql.getUserByIdSql}")
    String getUserByIdSql;

    @Value("${userDaoSql.getUserByLoginSql}")
    String getUserByLoginSql;

    @Value("${userDaoSql.addUserSql}")
    String addUserSql;

    @Value("${userDaoSql.updateUserSql}")
    String updateUserSql;

    @Value("${userDaoSql.deleteUserSql}")
    String deleteUserSql;

    private static String USER_LOGIN = "userLogin";
    private static String USER_PASSWORD = "userPasswd";
    private static String USER_DESCRIPTION = "userDesc";
    private static String USER_ID = "userId";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        LOGGER.debug("getAllUser()");

        return jdbcTemplate.query(getAllUsersSql, new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) throws DataAccessException {
        LOGGER.debug("getUserById({})", userId);

        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(USER_ID, userId);
            User user = namedParameterJdbcTemplate.queryForObject(getUserByIdSql, namedParameters, new UserRowMapper());

            return user;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    @Override
    public User getUserByLogin(String userLogin) throws DataAccessException {
        LOGGER.debug("getUserByLogin({})", userLogin);

        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(USER_LOGIN, userLogin);
            User user = namedParameterJdbcTemplate.queryForObject(getUserByLoginSql, namedParameters, new UserRowMapper());

            return user;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    @Override
    public Integer addUser(User user) throws DataAccessException {
        LOGGER.debug("addUser({})", user.getUserId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(USER_LOGIN, user.getLogin());
        parameterSource.addValue(USER_PASSWORD, user.getPassword());
        parameterSource.addValue(USER_DESCRIPTION, user.getDescription());
        parameterSource.addValue(USER_ID, user.getUserId());

        namedParameterJdbcTemplate.update(addUserSql, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateUser(User user) throws DataAccessException {
        LOGGER.debug("updateUser({})", user.getUserId());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(USER_LOGIN, user.getLogin());
        parameterSource.addValue(USER_PASSWORD, user.getPassword());
        parameterSource.addValue(USER_DESCRIPTION, user.getDescription());
        parameterSource.addValue(USER_ID, user.getUserId());

        if (namedParameterJdbcTemplate.update(updateUserSql, parameterSource) == 0)
            throw new DataAccessException("There is no user with ID " + Integer.toString(user.getUserId())){};
    }

    @Override
    public void deleteUser(Integer userId) throws DataAccessException {
        LOGGER.debug("deleteUser({})", userId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(USER_ID, userId);
        if (namedParameterJdbcTemplate.update(deleteUserSql, namedParameters) == 0)
            throw new DataAccessException("There is no user with ID " + Integer.toString(userId)) {};
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("description"));

            return user;
        }
    }
}
