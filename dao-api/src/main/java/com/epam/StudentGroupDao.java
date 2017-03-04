package com.epam;

import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * DAO interface for Student and Group.
 */
public interface StudentGroupDao {

    /**
     * Get students considering parameters (minGpa <= students.gpa <= maxGpa, students.group_id = groupId).
     *
     * @param minGpa
     * @param maxGpa
     * @param groupId
     * @return List of students.
     * @throws DataAccessException
     */
    List<Student> getStudents(Float minGpa, Float maxGpa, Integer groupId) throws DataAccessException;

    /**
     * Add student to DB.
     *
     * @param student
     * @return students ID.
     * @throws DataAccessException
     */
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
     * Return count of students in group, or all students if groupId is null.
     *
     * @param groupId
     * @return Count of students in group or all students if groupId is null.
     * @throws DataAccessException
     */
    Integer getStudentsCount(Integer groupId) throws DataAccessException;

    /**
     * Update student.
     *
     * @param student
     * @return Count of updated students.
     * @throws DataAccessException
     */
    Integer updateStudent(Student student) throws DataAccessException;

    /**
     * Delete student from DB.
     *
     * @param studentId
     * @return Count of deleted students.
     * @throws DataAccessException
     */
    Integer deleteStudent(Integer studentId) throws DataAccessException;

    /**
     * Get groups considering parameters (minGradDate <= group.graduation_date <= maxGradDate).
     *
     * @param minGradDate
     * @param maxGradDate
     * @return List of groups.
     * @throws DataAccessException
     */
    List<Group> getGroups(Date minGradDate, Date maxGradDate) throws DataAccessException;

    /**
     * Add new group to DB.
     *
     * @param group
     * @return Group ID.
     * @throws DataAccessException
     */
    Integer addGroup(Group group) throws DataAccessException;

    /**
     * Get group by ID.
     *
     * @param groupId
     * @return Group.
     * @throws DataAccessException
     */
    Group getGroupById(Integer groupId) throws DataAccessException;

    /**
     * Get group by name.
     *
     * @param groupName
     * @return Group.
     * @throws DataAccessException
     */
    Group getGroupByName(String groupName) throws DataAccessException;

    /**
     * Get count of groups considering parameters (minGradDate <= group.graduation_date <= maxGradDate).
     *
     * @return Count of groups.
     * @throws DataAccessException
     */
    Integer getGroupsCount(Date minGradDate, Date maxGradDate) throws DataAccessException;

    /**
     * Get students group GPA. If groupId is null, return GPA for all students.
     * @param groupId
     * @return Students group GPA or all students GPA if groupId is null.
     * @throws DataAccessException
     */
    Float getStudentsGpa(Integer groupId) throws DataAccessException;

    /**
     * Update group.
     *
     * @param group
     * @return Count of updated groups.
     * @throws DataAccessException
     */
    Integer updateGroup(Group group) throws DataAccessException;

    /**
     * Delete group from DB.
     *
     * @param groupId
     * @return Count of deleted group.
     * @throws DataAccessException
     */
    Integer deleteGroup(Integer groupId) throws DataAccessException;
}