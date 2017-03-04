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

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void addExistStudentTest() throws ExecutionException {
        LOGGER.debug("test: addExistStudentTest()");

        Student student = new Student(1,"Petro", 6.2f, 1);
        studentGroupDao.addStudent(student);
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
    public void updateNonexistentStudnt() throws Exception {
        LOGGER.debug("test: updateNonexistentStudnt()");

        Student student = new Student(1337, "Petro", 4.0f, 1);
        Integer updatedCount = studentGroupDao.updateStudent(student);

        assertTrue(updatedCount == 0);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        LOGGER.debug("test: deleteStudentTest()");

        studentGroupDao.deleteStudent(1);
        Student student = studentGroupDao.getStudentById(1);
        assertNull(student);
    }

    @Test
    public void deleteNonexistentStudentTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentStudentTest()");

        Integer deletedCount = studentGroupDao.deleteStudent(1337);
        assertTrue(deletedCount == 0);
    }

    @Test
    public void getGroupsTest() throws Exception {
        LOGGER.debug("test: getGroupsTest()");

        List<Group> groupList = studentGroupDao.getGroups(null, null);
        assertTrue(groupList.size() == 5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("01/01/2015");
        Date maxGradDate = sdf.parse("31/12/2018");

        groupList = studentGroupDao.getGroups(minGradDate, maxGradDate);
        assertTrue(groupList.size() == 3);
    }

    @Test
    public void addGroupTest() throws Exception {
        LOGGER.debug("test: addGroupTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Group group = new Group("II-13", sdf.parse("31/05/2019"));
        Integer groupId = studentGroupDao.addGroup(group);

        group = studentGroupDao.getGroupById(groupId);
        assertEquals(group.getName(), "II-13");
    }

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void addExistGroupTest() throws Exception {
        LOGGER.debug("test: addExistGroupTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Group group = new Group(1, "II-13", sdf.parse("31/05/2019"));
        Integer groupId = studentGroupDao.addGroup(group);
    }

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void addExistGroupByNameTest() throws Exception {
        LOGGER.debug("test: addExistGroupByNameTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Group group = new Group("II-12", sdf.parse("31/05/2018"));
        Integer groupId = studentGroupDao.addGroup(group);
    }

    @Test
    public void getGroupByNameTest() throws Exception {
        LOGGER.debug("test: getGroupByNameTest()");

        Group group = studentGroupDao.getGroupByName("II-12");
        assertTrue(group.getGroupId() == (Integer)1);
    }

    @Test
    public void getNonexistentGroupByName() throws Exception {
        LOGGER.debug("test: getNonexistentGroupByName()");

        Group group = studentGroupDao.getGroupByName("II-13");
        assertNull(group);
    }

    @Test
    public void updateGroupTest() throws Exception {
        LOGGER.debug("test: updateGroupTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Group group = studentGroupDao.getGroupById(2);
        group.setName("DreamTeam");
        group.setGraduationDate(sdf.parse("31/05/2017"));

        studentGroupDao.updateGroup(group);
        group = studentGroupDao.getGroupById(2);
        assertTrue(group.getName().equals("DreamTeam"));
        assertTrue(group.getGraduationDate().toString().equals("2017-05-31"));
    }

    @Test
    public void updateNonexistentGroupTest() throws Exception {
        LOGGER.debug("test: updateNonexistentGroupTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Group group = new Group("DreamTeam", sdf.parse("31/05/2017"));

        Integer updatedCount = studentGroupDao.updateGroup(group);
        group = studentGroupDao.getGroupById(6);

        assertEquals(updatedCount, (Integer)0);
        assertNull(group);
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
    public void deleteNonexistentGroupTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentGroupTest()");

        Integer deletedCount = studentGroupDao.deleteGroup(6);
        assertEquals(deletedCount, (Integer)0);
    }

    @Test
    public void getGroupCountTest() throws Exception {
        LOGGER.debug("test: getGroupCountTest()");

        Integer groupCount = studentGroupDao.getGroupsCount(null, null);
        assertEquals(groupCount, (Integer)5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("01/01/2015");
        Date maxGradDate = sdf.parse("31/12/2018");

        groupCount = studentGroupDao.getGroupsCount(minGradDate, maxGradDate);
        assertEquals(groupCount, (Integer)3);
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

        groupGpa = studentGroupDao.getStudentsGpa(null);
        studFromGroup = studentGroupDao.getStudents(null, null, null);

        studGpa = 0f;
        for (int i = 0; i < studFromGroup.size(); studGpa += studFromGroup.get(i++).getGpa());
        studGpa /= studFromGroup.size();

        assertEquals(groupGpa, studGpa);

        groupGpa = studentGroupDao.getStudentsGpa(6);
        assertNull(groupGpa);
    }

}