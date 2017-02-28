package com.epam;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * DAO interface for Student and Group.
 */
public interface StudentGroupDao {

    List<Student> getAllStudents() throws DataAccessException;

    Integer addStudent(Student student) throws DataAccessException;

    /**
     * Get student by ID.
     *
     * @param studentId student ID.
     * @return Student with following ID.
     * @throws DataAccessException
     */
    Student getStudentById(Integer studentId) throws DataAccessException;

    void updateStudent(Student student) throws DataAccessException;

    void deleteStudent(Integer studentId) throws DataAccessException;

    List<Group> getAllGroups() throws DataAccessException;

    Integer addGroup(Group group) throws DataAccessException;

    Group getGroupById(Integer groupId) throws DataAccessException;

    Group getGroupByName(String groupName) throws DataAccessException;

    void updateGroup(Group group) throws DataAccessException;

    void deleteGroup(Integer groupId) throws DataAccessException;

    void wireStudentAndGroup(Integer studentId, Integer groupId) throws DataAccessException;

    void deleteStudentFromGroup(Integer studentId) throws DataAccessException;

}