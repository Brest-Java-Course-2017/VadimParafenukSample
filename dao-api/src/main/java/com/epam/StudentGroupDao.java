package com.epam;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * DAO interface for Student and Group.
 */
public interface StudentGroupDao {

    List<Student> getStudents(Float minGpa, Float maxGpa, Integer groupId) throws DataAccessException;

    Integer addStudent(Student student) throws DataAccessException;

    /**
     * Get student by ID.
     *
     * @param studentId student ID.
     * @return Student with following ID.
     * @throws DataAccessException
     */
    Student getStudentById(Integer studentId) throws DataAccessException;

    /**
     * Return count of students in group, or all students if groupId is null
     *
     * @param groupId
     * @return Count of students in group or all students if groupId is null
     * @throws DataAccessException
     */
    Integer getStudentsCount(Integer groupId) throws DataAccessException;

    Integer updateStudent(Student student) throws DataAccessException;

    Integer deleteStudent(Integer studentId) throws DataAccessException;

    List<Group> getAllGroups() throws DataAccessException;

    Integer addGroup(Group group) throws DataAccessException;

    Group getGroupById(Integer groupId) throws DataAccessException;

    Group getGroupByName(String groupName) throws DataAccessException;

    Integer getGroupsCount() throws DataAccessException;

    /**
     * Get students group GPA. If groupId is null, return GPA for all students.
     * @param groupId
     * @return Students group GPA or all students GPA if groupId is null
     * @throws DataAccessException
     */
    Float getStudentsGpa(Integer groupId) throws DataAccessException;

    Integer updateGroup(Group group) throws DataAccessException;

    Integer deleteGroup(Integer groupId) throws DataAccessException;
}