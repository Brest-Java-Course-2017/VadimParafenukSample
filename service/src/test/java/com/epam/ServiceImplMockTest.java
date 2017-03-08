package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test-mock.xml"})
public class ServiceImplMockTest {

    private static final Logger LOGGER = LogManager.getLogger(ServiceImplMockTest.class);

    @Autowired
    private ServiceApi service;

    @Autowired
    private StudentGroupDao mockDao;

    @After
    public void clean() {
        verify();
    }

    @Before
    public void setUp() {
        reset(mockDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStudentById() throws Exception {
        LOGGER.debug("test: getStudentById()");

        expect(mockDao.getStudentById(null)).andThrow(new IllegalArgumentException("NULL ID"));
        replay(mockDao);

        service.getStudentById(null);
    }

    @Test
    public void addStudentTest() throws Exception {
        LOGGER.debug("test: addStudentTest()");

        Student student = new Student("VASIYA", 6.5f, 2);

        expect(mockDao.addStudent(student)).andReturn(18);
        expect(mockDao.getGroupById(student.getGroupId())).andReturn(new Group());
        replay(mockDao);

        Integer id = service.addStudent(student);

        assertEquals(id, (Integer)18);
    }

    @Test
    public void updateStudentTest() throws Exception {
        LOGGER.debug("test: updateStudentTest()");

        Student student = new Student(1, "VASILIY", 10f, 1);

        expect(mockDao.updateStudent(student)).andReturn(1);
        expect(mockDao.getStudentById(student.getStudentId())).andReturn(new Student());
        expect(mockDao.getGroupById(student.getGroupId())).andReturn(new Group());
        replay(mockDao);

        assertTrue(service.updateStudent(student) == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonexistentStudent() throws Exception {
        LOGGER.debug("test: deleteNonexistentStudent()");

        expect(mockDao.getStudentById(18)).andReturn(null);
        replay(mockDao);

        service.deleteStudent(18);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistGroup() throws Exception {
        LOGGER.debug("test: addExistGroup()");

        Group group = new Group("II-12", null);

        expect(mockDao.getGroupByName(group.getName())).andReturn(new Group());
        replay(mockDao);

        service.addGroup(group);
    }

    @Test
    public void updateGroupTest() throws Exception {
        LOGGER.debug("test: updateGroupTest()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Group group = new Group(1,"II-12", sdf.parse("31/05/2018"));

        expect(mockDao.getGroupById(group.getGroupId())).andReturn(new Group());
        expect(mockDao.updateGroup(group)).andReturn(1);
        replay(mockDao);

        assertTrue(service.updateGroup(group) == 1);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        LOGGER.debug("test: deleteStudentTest()");

        expect(mockDao.getGroupById(1)).andReturn(new Group());
        expect(mockDao.deleteGroup(1)).andReturn(1);
        replay(mockDao);

        assertTrue(service.deleteGroup(1) == 1);
    }

}
