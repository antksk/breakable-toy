package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @JsonGetter
 *  json으로 만들려는 객체의 get메소드를 기반으로 json properties를 만드는데,
 *  JsonGetter어노테이션이 지정된 get 메소드는 지정된 이름으로 json properties를 만듬
 **/
@Slf4j
public class JsonGetterTest {

    @AllArgsConstructor
    static class ExtendableBean {
        @Getter
        private int id;

        private String name;

        @JsonGetter("name")
        public String getTheName() {
            return name;
        }
    }


    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

        ExtendableBean bean = new ExtendableBean(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        log.debug("result json : {}", result);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));
    }
}
