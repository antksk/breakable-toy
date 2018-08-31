package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @JsonRawValue
 *  문자열로 저장되어 있는 json이 serialize 될때, JSON 형태로 변환됨
 **/
@Slf4j
public class _04_JsonRawValueTest {

    @Getter
    public class TestJsonObject {
        private int id;

        @JsonRawValue
        private String json;

        public TestJsonObject(int id){
            this.id = id;
            this.json = "{\"attr\":false}";
        }

        @JsonGetter("raw")
        public String getJson(){
            return json;
        }
    }

    @Test
    public void test()
            throws JsonProcessingException {

        TestJsonObject bean = new TestJsonObject(1);

        String result = new ObjectMapper().writeValueAsString(bean);

        log.debug("result json : {}", result);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("{\"attr\":false}"));
    }
}
