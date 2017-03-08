package com.epam.rest;

import com.epam.ServiceApi;
import com.epam.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:rest-mock-test.xml"})
public class RestMockTest {

    @Resource
    private StudentsGroupsController controller;

    private MockMvc mockMvc;

    @Autowired
    private ServiceApi service;


    @Before
    public void setUp() {
        mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(service);
        reset(service);
    }

    @Test
    public void addStudentTest() throws Exception {
        expect(service.addStudent(anyObject(Student.class))).andReturn(18);
        replay(service);

        String student = new ObjectMapper().writeValueAsString(new Student("VASILIY", 5f, 1));

        mockMvc.perform(
                post("/students")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("18"));
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        Student student = new Student(1, "VASILIY", 10f, 1);

        expect(service.getStudentById(anyInt())).andReturn(student);
        replay(service);

        mockMvc.perform(
                get("/students/id1")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudentTest() throws Exception {
        expect(service.updateStudent(anyObject(Student.class))).andReturn(1);
        replay(service);

        String student = new ObjectMapper().writeValueAsString(new Student(1,"VASILIY", 5f, 1));

        mockMvc.perform(
                put("/students")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student)
        ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }

}
