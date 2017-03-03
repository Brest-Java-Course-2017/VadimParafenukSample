package com.epam.dao;

import com.epam.Group;
import com.epam.Student;
import com.epam.StudentGroupDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class DaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(DaoImplTest.class.getName());

    @Autowired
    StudentGroupDao studentGroupDao;

    @Test
    public void getAllStudentsTest() throws Exception {
        LOGGER.debug("test: getAllStudentsTest()");

        List<Student> studentList = studentGroupDao.getStudents(null, null, null);
        assertTrue(studentList.size() == 17);

        studentList = studentGroupDao.getStudents(null, null, 1);
        assertTrue(studentList.size() == 3);

        studentList = studentGroupDao.getStudents(9f, 10f, 1);
        assertTrue(studentList.size() == 2);
    }

    @Test
    public void addStudentTest() throws Exception {
        LOGGER.debug("test: addStudentTest()");

        Student student = new Student("Petro", 6.2f, 1);
        Integer studentId = studentGroupDao.addStudent(student);

        student = studentGroupDao.getStudentById(studentId);
        assertTrue(student.getName().equals("Petro"));
    }

    @Test
    public void getStudentsCountTest() throws Exception {
        LOGGER.debug("test: getStudentsCountTest()");

        Integer studentsCount = studentGroupDao.getStudentsCount(null);
        assertEquals(studentsCount, (Integer)17);

        studentsCount = studentGroupDao.getStudentsCount(1);
        assertEquals(studentsCount, (Integer)3);
    }

    @Test
    public void updateStudentTest() throws Exception {
        LOGGER.debug("test: updateStudentTest()");

        Student student = studentGroupDao.getStudentById(2);
        student.setGpa(5.0f);

        studentGroupDao.updateStudent(student);
        student = studentGroupDao.getStudentById(2);
        assertTrue(student.getGpa() == 5.0f);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        LOGGER.debug("test: deleteStudentTest()");

        studentGroupDao.deleteStudent(1);
        Student student = studentGroupDao.getStudentById(1);
        assertNull(student);
    }

    @Test
    public void getAllGroupsTest() throws Exception {
        LOGGER.debug("test: deleteStudentTest()");

        List<Group> groupList = studentGroupDao.getAllGroups();
        assertTrue(groupList.size() == 5);
    }

    @Test
    public void addGroupTest() throws Exception {
        LOGGER.debug("test: addGroupTest()");

        Group group = new Group("II-13");
        Integer groupId = studentGroupDao.addGroup(group);

        group = studentGroupDao.getGroupById(groupId);
        assertEquals(group.getName(), "II-13");
    }

    @Test
    public void getGroupByNameTest() throws Exception {
        LOGGER.debug("test: getGroupByNameTest()");

        Group group = studentGroupDao.getGroupByName("II-12");
        assertTrue(group.getGroupId() == (Integer)1);
    }

    @Test
    public void updateGroupTest() throws Exception {
        LOGGER.debug("test: updateGroupTest()");

        Group group = studentGroupDao.getGroupById(2);
        group.setName("DreamTeam");

        studentGroupDao.updateGroup(group);
        group = studentGroupDao.getGroupById(2);
        assertTrue(group.getName() == "DreamTeam");
    }

    @Test
    public void deleteGroupTest() throws Exception {
        LOGGER.debug("test: deleteGroupTest()");

        studentGroupDao.deleteGroup(1);
        Group group = studentGroupDao.getGroupById(1);
        Student student = studentGroupDao.getStudentById(1);

        assertNull(group);
        assertNull(student); // Must also be deleted
    }

    @Test
    public void getGroupCountTest() throws Exception {
        LOGGER.debug("test: getGroupCountTest()");

        Integer groupCount = studentGroupDao.getGroupsCount();
        assertEquals(groupCount, (Integer)5);
    }

    @Test
    public void getStudentsGpaTest() throws Exception {
        LOGGER.debug("test: getGroupGpaTest()");

        Float groupGpa = studentGroupDao.getStudentsGpa(1);
        List<Student> studFromGroup = studentGroupDao.getStudents(null, null, 1);

        Float studGpa = 0f;

        for (int i = 0; i < studFromGroup.size(); studGpa += studFromGroup.get(i++).getGpa());
        studGpa /= studFromGroup.size();

        assertEquals(groupGpa, studGpa);
    }

}