package com.github.antksk.breakabletoy.json._0_jsa;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @JsonValue enum 전체 인스턴스를 serialize하는데 사용해야 하는 단일 메소드를 나타냄
 **/
@Slf4j
public class _06_JsonRootNameTest {

    @JsonRootName("user")
    @AllArgsConstructor
    @Getter
    public class TestJsonObject {

        private int id;
        private String name;
    }

    @Test
    public void test() throws JsonProcessingException
    {
        String result = new ObjectMapper()
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .writeValueAsString(new TestJsonObject(1, "test"));

        log.debug("{}",result);
        assertThat(result, containsString("\"user\""));
    }
}
