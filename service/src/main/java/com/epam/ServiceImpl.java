package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceImpl implements ServiceApi {

    private static final Logger LOGGER = LogManager.getLogger(ServiceImpl.class.getName());

    @Autowired
    private StudentGroupDao studentGroupDao;

    public void setStudentGroupDao(StudentGroupDao studentGroupDao) {
        this.studentGroupDao = studentGroupDao;
    }

    public List<Student> getStudents(Float minGpa, Float maxGpa, Integer groupId) throws DataAccessException {
        LOGGER.debug("getStudents({}, {}, {})", minGpa, maxGpa, groupId);
        return studentGroupDao.getStudents(minGpa, maxGpa, groupId);
    }

    public Integer addStudent(Student student) throws DataAccessException {
        LOGGER.debug("addStudent(" + student + ")");

        Assert.notNull(student, "Student should not be null");
        Assert.isNull(student.getStudentId(), "Student ID should be null");
        Assert.hasText(student.getName(), "Student should has name");
        Assert.notNull(student.getGpa(), "Student should has GPA");
        Assert.state(student.getGpa() >= 0 && student.getGpa() <= 10, "Student should has correct GPA.");
        Assert.notNull(student.getGroupId(), "Student should has group");

        Group group = studentGroupDao.getGroupById(student.getGroupId());
        Assert.notNull(group, "Student should has correct group");

        return studentGroupDao.addStudent(student);
    }

    public Student getStudentById(Integer studentId) throws DataAccessException {
        LOGGER.debug("getStudentById({})", studentId);

        Assert.notNull(studentId, "studentId should not be null");
        return studentGroupDao.getStudentById(studentId);
    }

    public Integer getStudentsCount(Integer groupId) throws DataAccessException {
        LOGGER.debug("getStudentsCount({})", groupId);

        if (groupId != null) {
            Group group = studentGroupDao.getGroupById(groupId);
            Assert.notNull(group, "groupId should be correct");
        }

        return studentGroupDao.getStudentsCount(groupId);
    }

    public Integer updateStudent(Student student) throws DataAccessException {
        LOGGER.debug("updateStudent(" + student + ")");

        Assert.notNull(student, "Student should not be null");
        Assert.notNull(student.getStudentId(), "Student ID should not be null");

        Student existStudent = studentGroupDao.getStudentById(student.getStudentId());
        Assert.notNull(existStudent, "Student ID should be correct");

        Assert.hasText(student.getName(), "Student should has name");
        Assert.notNull(student.getGpa(), "Student should has GPA");
        Assert.state(student.getGpa() >= 0 && student.getGpa() <= 10, "Student should has correct GPA.");
        Assert.notNull(student.getGroupId(), "Student should has group");

        Group group = studentGroupDao.getGroupById(student.getGroupId());
        Assert.notNull(group, "Student should has correct group");

        return studentGroupDao.updateStudent(student);
    }

    public Integer deleteStudent(Integer studentId) throws DataAccessException {
        LOGGER.debug("deleteStudent({})", studentId);

        Assert.notNull(studentId, "studentId should not be null");
        Student student = studentGroupDao.getStudentById(studentId);
        Assert.notNull(student, "Student with studentId should be correct");

        return studentGroupDao.deleteStudent(studentId);
    }

    public List<Group> getGroups(Date minGradDate, Date maxGradDate) throws DataAccessException {
        LOGGER.debug("getGroups({}, {})", minGradDate, maxGradDate);
        return studentGroupDao.getGroups(minGradDate, maxGradDate);
    }

    public Integer addGroup(Group group) throws DataAccessException {
        LOGGER.debug("addGroup(" + group + ")");

        Assert.notNull(group, "Group should not be null");
        Assert.isNull(group.getGroupId(), "Group ID should be null");
        Assert.hasText(group.getName(), "Group should has name");
        Assert.notNull(group.getGraduationDate(), "Group should has graduation date");

        try {
            if (studentGroupDao.getGroupByName(group.getName()) != null)
                throw new IllegalArgumentException("Group with name " + group.getName() + " already exist");
        } catch (DataAccessException e) {
            // Все отлично, так и должно быть
        }

        return studentGroupDao.addGroup(group);
    }

    public Group getGroupById(Integer groupId) throws DataAccessException {
        LOGGER.debug("getGroupById({})", groupId);

        Assert.notNull(groupId, "groupId should not be null");
        return studentGroupDao.getGroupById(groupId);
    }

    public Group getGroupByName(String groupName) throws DataAccessException {
        LOGGER.debug("getGroupByName({})", groupName);

        Assert.notNull(groupName, "groupName should not be null");
        return studentGroupDao.getGroupByName(groupName);
    }

    public Integer getGroupsCount(Date minGradDate, Date maxGradDate) throws DataAccessException {
        LOGGER.debug("getGroupsCount({}, {})", minGradDate, maxGradDate);

        return studentGroupDao.getGroupsCount(minGradDate, maxGradDate);
    }

    public Float getStudentsGpa(Integer groupId) throws DataAccessException {
        LOGGER.debug("getStudentsGpa({})", groupId);

        return studentGroupDao.getStudentsGpa(groupId);
    }

    public Integer updateGroup(Group group) throws DataAccessException {
        LOGGER.debug("updateGroup({})", group);

        Assert.notNull(group, "Group should not be null");
        Assert.notNull(group.getGroupId(), "Group ID should not be null");
        Assert.hasText(group.getName(), "Group should has name");
        Assert.notNull(group.getGraduationDate(), "Group should has graduation date");

        Group existGroup = studentGroupDao.getGroupById(group.getGroupId());
        Assert.notNull(existGroup, "Group should be correct");

        return studentGroupDao.updateGroup(group);
    }

    public Integer deleteGroup(Integer groupId) throws DataAccessException {
        LOGGER.debug("deleteGroup({})", groupId);

        Assert.notNull(groupId, "groupId should not be null");

        Group group = studentGroupDao.getGroupById(groupId);
        Assert.notNull(group, "Group should be correct");

        return studentGroupDao.deleteGroup(groupId);
    }
}