package com.github.antksk.breakabletoy.json._0_jsa;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @JsonGetter
 *  json으로 만들려는 객체의 get메소드를 기반으로 json properties를 만드는데,
 *  JsonGetter어노테이션이 지정된 get 메소드는 지정된 이름으로 json properties를 만듬
 **/
@Slf4j
public class _02_JsonGetterTest {

    @AllArgsConstructor
    static class TestJsonObject {
        @Getter
        private int id;

        private String name;

        @JsonGetter("name")
        public String getTheName() {
            return name;
        }
    }


    @Test
    public void test()
            throws JsonProcessingException {

        TestJsonObject json = new TestJsonObject(1, "My json");

        String result = new ObjectMapper().writeValueAsString(json);

        log.debug("result json : {}", result);

        assertThat(result, containsString("My json"));
        assertThat(result, containsString("1"));
    }
}
