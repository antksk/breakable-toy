package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Slf4j
public class _04_JsonSetterTest {

    public static final class TestJsonObject {

        private String name;

        @JsonSetter("name")
        public TestJsonObject theName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("name", name)
                    .build();
        }

    }

    @Test
    public void test()
            throws IOException {

        String json = "{\"name\":\"My bean\"}";

        TestJsonObject bean = new ObjectMapper()
                .readerFor(TestJsonObject.class)
                .readValue(json);


        log.debug("{}", bean);

        assertEquals("My bean", bean.name);
    }
}
