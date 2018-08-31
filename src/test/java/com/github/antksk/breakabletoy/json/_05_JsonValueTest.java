package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @JsonValue enum 전체 인스턴스를 serialize하는데 사용해야 하는 단일 메소드를 나타냄
 **/
@Slf4j
public class _05_JsonValueTest {

    @AllArgsConstructor
    public enum TestJsonObject {
        TYPE1(1, "Type A");

        private int id;
        private String name;

        @JsonValue
        public String getName() {
            return name;
        }
    }

    @Test
    public void test() throws IOException

    {
        String enumAsString = new ObjectMapper().writeValueAsString(TestJsonObject.TYPE1);
        log.debug("{}",enumAsString);
        assertThat(enumAsString, is("\"Type A\""));
    }
}
