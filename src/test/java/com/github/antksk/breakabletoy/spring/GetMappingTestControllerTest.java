package com.github.antksk.breakabletoy.spring;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(GetMappingTestController.class)
public class GetMappingTestControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 동명의 underscore 스타일과 lowerCamel 스타일의 파라미터가 컨트롤러에 들어왔을때,
     * 정상적으로 값을 가져 올수 있도록 코드 작성
     */
    @Test
    public void underscore_to_lower_camel_test() throws Exception{
        System.out.println("user_name : " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_name"));
        System.out.println("userName : " + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, "userName"));

        mvc.perform(get("/test/param").accept(MediaType.TEXT_PLAIN).param("user_name","underscore param"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(HttpServletRequest request)"))
        ;
        mvc.perform(get("/test/param").accept(MediaType.TEXT_PLAIN).param("userName","camel param"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(HttpServletRequest request)"))
        ;
    }


    /**
     * 같은 url로 다른 이름의 파라미터가 들어왔을때, 각 각 다른 메소드가 동작하는지 테스트
     */
    @Test
    public void same_param_test() throws Exception {
        mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN).param("a","test code"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(@RequestParam String a)"))
        ;
        mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN).param("b","1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("test(@RequestParam Integer b)"))
        ;
    }


}