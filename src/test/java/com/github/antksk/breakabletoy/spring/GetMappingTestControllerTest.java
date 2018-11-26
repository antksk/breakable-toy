package com.github.antksk.breakabletoy.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GetMappingTestController.class)
public class GetMappingTestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_url_param_a() throws Exception {
        mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN).param("a","test code"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(@RequestParam String a)"))
        ;
    }

    @Test
    public void test_url_param_b() throws Exception {
        mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN).param("b","1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(@RequestParam Integer b)"))
        ;
    }

}