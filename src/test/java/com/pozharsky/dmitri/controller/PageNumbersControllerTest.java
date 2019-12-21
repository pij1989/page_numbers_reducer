package com.pozharsky.dmitri.controller;

import com.pozharsky.dmitri.configuration.TestAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@WebAppConfiguration
public class PageNumbersControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenReducedPageNumbersURIWithRequestParam_whenMockMVC_thenReturnsJSPViewNameAndModelAttribute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reducedPageNumbers")
                .param("rawPageNumbers","1,3,32,5,11,7,6,19,2,21,4,8,22,23"))
                .andExpect(view().name("reduceNumbersPage"))
                .andExpect(model().attribute("reducePageNumbers","1-8,11,19,21-23,32"));
    }

    @Test
    public void givenReducedPageNumbersURIWithRequestParam_whenMockMVC_thenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reducedPageNumbers")
                .param("rawPageNumbers","1,3,32,5,11,7,6,19,2,21,4,8,22,23"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenReducedPageNumbersURIWithBadRequestParam_whenMockMVC_thenError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/reducedPageNumbers")
                .param("rawPageNumbers","01,3,32-,5,11,7,6,19,2,21,4,8,22,23"))
                .andExpect(status().isInternalServerError());

    }
}