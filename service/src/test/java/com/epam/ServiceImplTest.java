package com.epam;

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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class ServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger(ServiceImplTest.class.getName());

    @Autowired
    ServiceApi service;

    @Test
    public void getAllStudentsTest() throws Exception {
        LOGGER.debug("test: getAllStudentsTest");

        List<Student> studentList = service.getStudents(null, null, null);
        assertEquals((Integer)studentList.size(), (Integer)17);

        studentList = service.getStudents(null, null, 1);
        assertEquals((Integer)studentList.size(), (Integer)3);

        studentList = service.getStudents(8f, 9f, null);
        assertEquals((Integer)studentList.size(), (Integer)4);
    }

    @Test
    public void addStudentTest() throws Exception {
        LOGGER.debug("test: addStudentTest");

        Student student = new Student("Petro", 6f, 1);
        Integer petroId = service.addStudent(student);

        student = service.getStudentById(petroId);
        assertEquals(student.getName(), "Petro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addBadStudentTest() throws Exception {
        LOGGER.debug("test: addBadStudentTest");

        Student student = new Student(18, "Petro", 6f, 1);
        service.addStudent(student);
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        LOGGER.debug("test: getStudentByIdTest");

        Student student = service.getStudentById(1);
        assertEquals(student.getName(), "Vadim");
    }

    @Test
    public void getStudentByNonexistentIdTest() throws Exception {
        LOGGER.debug("test: getStudentByNonexistentIdTest");

        Student student = service.getStudentById(18);
        assertNull(student);
    }

    @Test
    public void getStudentsCountTest() throws Exception {
        LOGGER.debug("test: getStudentsCountTest");

        Integer count = service.getStudentsCount(null);
        assertEquals(count, (Integer)17);

        count = service.getStudentsCount(1);
        assertEquals(count, (Integer)3);
    }

    @Test
    public void updateStudentTest() throws Exception {
        LOGGER.debug("test: updateStudentTest");

        Student student = new Student(1, "Updated", 4f, 2);
        service.updateStudent(student);

        student = service.getStudentById(1);
        assertEquals(student.getName(), "Updated");

        Integer ii12Count = service.getStudentsCount(1);
        assertEquals(ii12Count, (Integer)2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNonexistentStudentTest() throws Exception {
        LOGGER.debug("test: updateNonexistentStudentTest");

        Student student = new Student(18, "Updated", 4f, 2);
        service.updateStudent(student);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        LOGGER.debug("test: deleteStudentTest");

        service.deleteStudent(1);
        Student student = service.getStudentById(1);

        assertNull(student);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonexistentStudentTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentStudentTest");

        service.deleteStudent(18);
    }

    @Test
    public void getGroupsTest() throws Exception {
        LOGGER.debug("test: getGroupsTest");

        List<Group> groupList = service.getGroups(null, null);
        assertEquals((Integer)groupList.size(), (Integer)5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("01/01/2015");
        Date maxGradDate = sdf.parse("31/12/2018");

        groupList = service.getGroups(minGradDate, maxGradDate);
        assertEquals((Integer)groupList.size(), (Integer)3);
    }

    @Test
    public void addGroupTest() throws Exception {
        LOGGER.debug("test: addGroupTest");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("31/05/2019");

        Group group = new Group("II-13", minGradDate);
        Integer ii13Id = service.addGroup(group);

        group = service.getGroupById(ii13Id);
        assertEquals(group.getName(), "II-13");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addBadGroupTest() throws Exception {
        LOGGER.debug("test: addBadGroupTest");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("31/05/2019");

        Group group = new Group("II-12", minGradDate);
        service.addGroup(group);
    }

    @Test
    public void getGroupByNonexistentIdTest() throws Exception {
        LOGGER.debug("test: getGroupByNonexistentIdTest");

        Group group = service.getGroupById(6);
        assertNull(group);
    }

    @Test
    public void getGroupByNameTest() throws Exception {
        LOGGER.debug("test: getGroupByNameTest");

        Group group = service.getGroupByName("II-12");
        assertEquals(group.getName(), "II-12");
    }

    @Test
    public void getGroupByNonexistentNameTest() throws Exception {
        LOGGER.debug("test: getGroupByNonexistentNameTest");

        Group group = service.getGroupByName("II-13");
        assertNull(group);
    }

    @Test
    public void getGroupsCountTest() throws Exception {
        LOGGER.debug("test: getGroupsCountTest");

        Integer gropsCount = service.getGroupsCount(null, null);
        assertEquals(gropsCount, (Integer)5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date minGradDate = sdf.parse("01/01/2015");
        Date maxGradDate = sdf.parse("31/12/2018");

        gropsCount = service.getGroupsCount(minGradDate, maxGradDate);
        assertEquals(gropsCount, (Integer)3);
    }

    @Test
    public void getStudentsGpaTest() throws Exception {
        LOGGER.debug("test: getStudentsGpaTest");

        Float groupGpa = service.getStudentsGpa(1);
        List<Student> studFromGroup = service.getStudents(null, null, 1);

        Float studGpa = 0f;
        for (int i = 0; i < studFromGroup.size(); studGpa += studFromGroup.get(i++).getGpa());
        studGpa /= studFromGroup.size();

        assertEquals(groupGpa, studGpa);

        groupGpa = service.getStudentsGpa(null);
        studFromGroup = service.getStudents(null, null, null);

        studGpa = 0f;
        for (int i = 0; i < studFromGroup.size(); studGpa += studFromGroup.get(i++).getGpa());
        studGpa /= studFromGroup.size();

        assertEquals(groupGpa, studGpa);

        groupGpa = service.getStudentsGpa(6);
        assertNull(groupGpa);
    }

    @Test
    public void updateGroupTest() throws Exception {
        LOGGER.debug("test: updateGroupTest");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        Group group = new Group(1, "II-12(Updated)", sdf.parse("01/07/2018"));
        service.updateGroup(group);

        group = service.getGroupById(1);
        assertEquals(group.getName(), "II-12(Updated)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNonexistentGroupTest() throws Exception {
        LOGGER.debug("test: updateNonexistentGroupTest");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        Group group = new Group(6, "II-13", sdf.parse("01/07/2018"));
        service.updateGroup(group);
    }

    @Test
    public void deleteGroupTest() throws Exception {
        LOGGER.debug("test: deleteGroupTest");

        service.deleteGroup(1);
        Group group = service.getGroupById(1);

        assertNull(group);

        Integer allStudentsCount = service.getStudentsCount(null);
        assertEquals(allStudentsCount, (Integer)14); // Students from group 1 should also be deleted
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonexistentGroupTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentGroupTest");

        service.deleteGroup(6);
    }

}