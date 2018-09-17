package com.github.antksk.breakabletoy.json._2_jpia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class _02_JsonIgnoreTest {

    @AllArgsConstructor
    @Getter
    public static final class TestJsonObject {
        @JsonIgnore
        private final int id;
        private final  String name;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", id)
                    .append("name", name)
                    .build();
        }
    }

    @Test
    public void test()
            throws JsonProcessingException {

        TestJsonObject bean = new TestJsonObject(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);


        log.debug("result json : {}", result);
        log.debug("bean : {}", bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));
    }
}
