package com.epam;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    Student testStudent;

    @Before
    public void setup() {
        testStudent = new Student();
    }

    @Test
    public void getStudentId() throws Exception {
        testStudent.setStudentId(1);
        assertEquals(testStudent.getStudentId(), (Integer)1);
    }

    @Test
    public void getName() throws Exception {
        testStudent.setName("Vadim");
        assertEquals(testStudent.getName(), "Vadim");
    }

    @Test
    public void getGpa() throws Exception {
        testStudent.setGpa(8.0f);
        assertEquals(testStudent.getGpa(), (Float)8.0f);
    }

    @Test
    public void getGroupTest() throws Exception {
        testStudent.setGroupId(1);
        assertEquals(testStudent.getGroupId(), (Integer)1);
    }
}