package com.epam.dao;

import com.epam.Group;
import com.epam.Student;
import com.epam.StudentGroupDao;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class DaoImpl implements StudentGroupDao {

    @Value("${StudentGroupDaoSql.getStudents}")
    String getStudentsSql;

    @Value("${StudentGroupDaoSql.addStudent}")
    String addStudentSql;

    @Value("${StudentGroupDaoSql.getStudentsCount}")
    String getStudentsCountSql;

    @Value("${StudentGroupDaoSql.getStudentById}")
    String getStudentByIdSql;

    @Value("${StudentGroupDaoSql.updateStudent}")
    String updateStudentSql;

    @Value("${StudentGroupDaoSql.deleteStudent}")
    String deleteStudentSql;

    @Value("${StudentGroupDaoSql.getAllGroups}")
    String getAllGroupsSql;

    @Value("${StudentGroupDaoSql.addGroup}")
    String addGroupSql;

    @Value("${StudentGroupDaoSql.getGroupsCount}")
    String getGroupsCountSql;

    @Value("${StudentGroupDaoSql.getStudentsGpa}")
    String getStudentsGpaSql;

    @Value("${StudentGroupDaoSql.getGroupById}")
    String getGroupByIdSql;

    @Value("${StudentGroupDaoSql.getGroupByName}")
    String getGroupByNameSql;

    @Value("${StudentGroupDaoSql.updateGroup}")
    String updateGroupSql;

    @Value("${StudentGroupDaoSql.deleteGroup}")
    String deleteGroupSql;

    private static final String STUDENT_ID = "studentId";
    private static final String STUDENT_NAME = "studentName";
    private static final String GPA = "gpa";
    private static final String MIN_GPA = "minGpa";
    private static final String MAX_GPA = "maxGpa";
    private static final String GROUP_ID = "groupId";
    private static final String GROUP_NAME = "groupName";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public  List<Student> getStudents(Float minGpa, Float maxGpa, Integer groupId) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(MIN_GPA, minGpa);
        parameterSource.addValue(MAX_GPA, maxGpa);
        parameterSource.addValue(GROUP_ID, groupId);

        return namedParameterJdbcTemplate.query(getStudentsSql, parameterSource, new StudentRowMapper());
    }

    public Integer addStudent(Student student) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(STUDENT_ID, student.getStudentId());
        parameterSource.addValue(STUDENT_NAME, student.getName());
        parameterSource.addValue(GPA, student.getGpa());
        parameterSource.addValue(GROUP_ID, student.getGroupId());

        namedParameterJdbcTemplate.update(addStudentSql, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Student getStudentById(Integer studentId) throws DataAccessException {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(STUDENT_ID, studentId);
            Student student = namedParameterJdbcTemplate.queryForObject(getStudentByIdSql, namedParameters, new StudentRowMapper());

            return student;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    public Integer getStudentsCount(Integer groupId) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(GROUP_ID, groupId);

        return namedParameterJdbcTemplate.queryForObject(getStudentsCountSql, parameterSource, Integer.class);
    }

    public Integer updateStudent(Student student) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(STUDENT_ID, student.getStudentId());
        parameterSource.addValue(STUDENT_NAME, student.getName());
        parameterSource.addValue(GPA, student.getGpa());
        parameterSource.addValue(GROUP_ID, student.getGroupId());

        return namedParameterJdbcTemplate.update(updateStudentSql, parameterSource);
    }

    public Integer deleteStudent(Integer studentId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(STUDENT_ID, studentId);
        return namedParameterJdbcTemplate.update(deleteStudentSql, namedParameters);
    }

    public List<Group> getAllGroups() throws DataAccessException {
        return jdbcTemplate.query(getAllGroupsSql, new GroupRowMapper());
    }

    public Integer addGroup(Group group) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(GROUP_ID, group.getGroupId());
        parameterSource.addValue(GROUP_NAME, group.getName());

        namedParameterJdbcTemplate.update(addGroupSql, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Group getGroupById(Integer groupId) throws DataAccessException {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(GROUP_ID, groupId);
            Group group = namedParameterJdbcTemplate.queryForObject(getGroupByIdSql, namedParameters, new GroupRowMapper());

            return group;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    public Group getGroupByName(String groupName) throws DataAccessException {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(GROUP_NAME, groupName);
            Group group = namedParameterJdbcTemplate.queryForObject(getGroupByNameSql, namedParameters, new GroupRowMapper());

            return group;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }
    @Override
    public Integer getGroupsCount() throws DataAccessException {
        return jdbcTemplate.queryForObject(getGroupsCountSql, Integer.class);
    }

    @Override
    public Float getStudentsGpa(Integer groupId) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(GROUP_ID, groupId);

        return namedParameterJdbcTemplate.queryForObject(getStudentsGpaSql, parameterSource, Float.class);
    }

    public Integer updateGroup(Group group) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(GROUP_ID, group.getGroupId());
        parameterSource.addValue(GROUP_NAME, group.getName());

        return namedParameterJdbcTemplate.update(updateGroupSql, parameterSource);
    }

    public Integer deleteGroup(Integer groupId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(GROUP_ID, groupId);
        return namedParameterJdbcTemplate.update(deleteGroupSql, namedParameters);
    }

    private class GroupRowMapper implements RowMapper<Group> {

        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            Group group = new Group(resultSet.getInt("group_id"),
                    resultSet.getString("name"));

            return group;
        }
    }

    private class StudentRowMapper implements RowMapper<Student> {

        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student(resultSet.getInt("student_id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("gpa"),
                    resultSet.getInt("group_id"));

            return student;
        }
    }
}
