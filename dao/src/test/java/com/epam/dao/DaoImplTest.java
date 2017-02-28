package com.epam.dao;

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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class DaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(DaoImplTest.class.getName());

    @Autowired
    StudentGroupDao studentGroupDao;

    @Test
    public void getAllStudents() throws Exception {
        List<Student> studentList = studentGroupDao.getAllStudents();
        assertTrue(studentList.size() == 10);
    }

    @Test
    public void addStudent() throws Exception {

    }

    @Test
    public void getStudentById() throws Exception {

    }

    @Test
    public void updateStudent() throws Exception {

    }

    @Test
    public void deleteStudent() throws Exception {

    }

    @Test
    public void getAllGroups() throws Exception {

    }

    @Test
    public void addGroup() throws Exception {

    }

    @Test
    public void getGroupById() throws Exception {

    }

    @Test
    public void getGroupByName() throws Exception {

    }

    @Test
    public void updateGroup() throws Exception {

    }

    @Test
    public void deleteGroup() throws Exception {

    }

    @Test
    public void wireStudentAndGroup() throws Exception {

    }

    @Test
    public void deleteStudentFromGroup() throws Exception {

    }

}