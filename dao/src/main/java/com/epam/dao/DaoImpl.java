package com.epam.dao;

import com.epam.Group;
import com.epam.Student;
import com.epam.StudentGroupDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoImpl implements StudentGroupDao {

    @Value("${StudentGroupDaoSql.getAllStudents}")
    String getAllStudentsSql;

    @Value("${StudentGroupDaoSql.addStudent}")
    String addStudentSql;

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

    @Value("${StudentGroupDaoSql.getGroupById}")
    String getGroupByIdSql;

    @Value("${StudentGroupDaoSql.getGroupByName}")
    String getGroupByNameSql;

    @Value("${StudentGroupDaoSql.updateGroup}")
    String updateGroupSql;

    @Value("${StudentGroupDaoSql.deleteGroup}")
    String deleteGroupSql;

    @Value("${StudentGroupDaoSql.wireStudentAndGroup}")
    String wireStudentAndGroupSql;

    @Value("${StudentGroupDaoSql.deleteStudentFromGroup}")
    String deleteStudentFromGroup;

    private static final String STUDENT_ID = "studentId";
    private static final String STUDENT_NAME = "studentName";
    private static final String GPA = "gpa";
    private static final String GROUP_ID = "groupId";
    private static final String GROUP_NAME = "groupName";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        return jdbcTemplate.query(getAllStudentsSql, new StudentUserRowMapper());
    }

    @Override
    public Integer addStudent(Student student) throws DataAccessException {
        return null;
    }

    @Override
    public Student getStudentById(Integer studentId) throws DataAccessException {
        return null;
    }

    @Override
    public void updateStudent(Student student) throws DataAccessException {

    }

    @Override
    public void deleteStudent(Integer studentId) throws DataAccessException {

    }

    @Override
    public List<Group> getAllGroups() throws DataAccessException {
        return null;
    }

    @Override
    public Integer addGroup(Group group) throws DataAccessException {
        return null;
    }

    @Override
    public Group getGroupById(Integer groupId) throws DataAccessException {
        return null;
    }

    @Override
    public Group getGroupByName(String groupName) throws DataAccessException {
        return null;
    }

    @Override
    public void updateGroup(Group group) throws DataAccessException {

    }

    @Override
    public void deleteGroup(Integer groupId) throws DataAccessException {

    }

    @Override
    public void wireStudentAndGroup(Integer studentId, Integer groupId) throws DataAccessException {

    }

    @Override
    public void deleteStudentFromGroup(Integer studentId) throws DataAccessException {

    }

    private class StudentUserRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student user = new Student(resultSet.getInt("student_id"),
                                       resultSet.getString("name"),
                                       resultSet.getFloat("gpa"));

            return user;
        }

    }

}
