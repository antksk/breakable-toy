package com.github.antksk.breakabletoy.json._2_jpia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

@Slf4j
public class _01_JsonIgnorePropertiesTest {

    @JsonIgnoreProperties({ "id", "test" })
    @Getter
    public static final class TestJsonObject {
        private final int id;
        private final String name;
        private final List<Object> test;

        @JsonCreator
        public TestJsonObject(
                @JsonProperty("id") int id,
                @JsonProperty("theName") String name,
                @JsonProperty(value="array",required = false, defaultValue = "[]") List<Object> test
        ) {
            this.id = id;
            this.name = name;
            this.test = test;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", id)
                    .append("name", name)
                    .append("test", test)
                    .build();
        }
    }


    @Test
    public void test() throws IOException {
/*
        "{
            \"id\":1,
            \"theName\":\"My bean\",
            \"array\":[
                1,
                2,
                3,
                4,
                \"5\",
                {
                    \"id\":1,
                    \"theName\":\"My bean\"
                }
            ]
       }";
*/
        TestJsonObject bean = new TestJsonObject(1, "My bean", Arrays.asList(new Object[]{
                1,
                2,
                3,
                4,
                "5",
                ImmutableMap.builder()
                        .put("id",1)
                        .put("theName", "My bean")
                        .build()
        }));


        String result = new ObjectMapper()
                .writeValueAsString(bean);


        log.debug("result json : {}", result);
        log.debug("bean : {}", bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));

    }
}
