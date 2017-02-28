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

    public List<Student> getAllStudents() throws DataAccessException {
        return jdbcTemplate.query(getAllStudentsSql, new StudentRowMapper());
    }

    public List<Student> getStudentFromGroup(Group group) throws DataAccessException {
        return null;
    }

    public Integer addStudent(Student student) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(STUDENT_ID, student.getStudentId());
        parameterSource.addValue(STUDENT_NAME, student.getName());
        parameterSource.addValue(GPA, student.getGpa());

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

    public void updateStudent(Student student) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(STUDENT_ID, student.getStudentId());
        parameterSource.addValue(STUDENT_NAME, student.getName());
        parameterSource.addValue(GPA, student.getGpa());

        if (namedParameterJdbcTemplate.update(updateStudentSql, parameterSource) == 0)
            throw new DataAccessException("There is no student with ID " + Integer.toString(student.getStudentId())){};
    }

    public void deleteStudent(Integer studentId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(STUDENT_ID, studentId);
        if (namedParameterJdbcTemplate.update(deleteStudentSql, namedParameters) == 0)
            throw new DataAccessException("There is no student with ID " + Integer.toString(studentId)) {};

//        List<StudentInGroup> studentInGroups = jdbcTemplate.query("select * from students_in_groups", new StudentInGroupRowMapper());
    }

    public List<Group> getAllGroups() throws DataAccessException {
        return null;
    }

    public Integer addGroup(Group group) throws DataAccessException {
        return null;
    }

    public Group getGroupById(Integer groupId) throws DataAccessException {
        return null;
    }

    public Group getGroupByName(String groupName) throws DataAccessException {
        return null;
    }

    public void updateGroup(Group group) throws DataAccessException {

    }

    public void deleteGroup(Integer groupId) throws DataAccessException {

    }

    public void wireStudentAndGroup(Integer studentId, Integer groupId) throws DataAccessException {

    }

    public void deleteStudentFromGroup(Integer studentId) throws DataAccessException {

    }

//    private class StudentInGroup {
//
//        private Integer studentId;
//        private Integer groupId;
//
//        public StudentInGroup(Integer studentId, Integer groupId) {
//            this.studentId = studentId;
//            this.groupId = groupId;
//        }
//
//        public Integer getStudentId() {
//            return studentId;
//        }
//
//        public void setStudentId(Integer studentId) {
//            this.studentId = studentId;
//        }
//
//        public Integer getGroupId() {
//            return groupId;
//        }
//
//        public void setGroupId(Integer groupId) {
//            this.groupId = groupId;
//        }
//    }
//
//    private class StudentInGroupRowMapper implements RowMapper<StudentInGroup> {
//        @Override
//        public StudentInGroup mapRow(ResultSet resultSet, int i) throws SQLException {
//            StudentInGroup studentInGroup = new StudentInGroup(resultSet.getInt("student_id"),
//                    resultSet.getInt("group_id"));
//
//            return studentInGroup;
//        }
//    }

    private class GroupRowMapper implements RowMapper<Group> {

        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            Group group = new Group(resultSet.getInt("group_id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("speciality"));

            return group;
        }
    }

    private class StudentRowMapper implements RowMapper<Student> {

        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student(resultSet.getInt("student_id"),
                                       resultSet.getString("name"),
                                       resultSet.getFloat("gpa"));

            return student;
        }
    }

}
