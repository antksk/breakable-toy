package com.github.antksk.breakabletoy.json._0_jsa;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @JsonPropertyOrder
 *  json properties의 표시 순서를 지정
 **/
@Slf4j
public class _03_JsonPropertyOrderTest {

    @JsonPropertyOrder({ "name", "id" })
    @Getter
    @AllArgsConstructor
    public class TestJsonObject {
        private int id;
        private String name;
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
