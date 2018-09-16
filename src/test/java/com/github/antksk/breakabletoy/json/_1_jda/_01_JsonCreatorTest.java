package com.github.antksk.breakabletoy.json._1_jda;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@Slf4j
public class _01_JsonCreatorTest {
    @Getter
    public static final class TestJsonObject0 {
        private final int id;
        private final String name;
        private final List<Object> test;

        @JsonCreator
        public TestJsonObject0(
                @JsonProperty("id") int id,
                @JsonProperty("theName") String name,
                @JsonProperty(value="array",required = false, defaultValue = "[]") List<Object> test
        ) {
            this.id = id;
            this.name = name;
            this.test = test;
            test.forEach(e->log.debug("{} = {}",e.getClass().getName(), e));
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

    @Getter
    public static final class TestJsonObject1 {
        private final List<String> test;

        public TestJsonObject1(@JsonProperty("array") List<String> test) {
            this.test = test;
        }
    }

    @Test
    public void test() throws IOException {

        String json = "{\"id\":1,\"theName\":\"My bean\", \"array\":[1,2,3,4,\"5\", {\"id\":1,\"theName\":\"My bean\"}]}";

        TestJsonObject0 bean = new ObjectMapper()
                .readerFor(TestJsonObject0.class)
                .readValue(json);

        log.debug("bean : {}", bean);

        assertEquals("My bean", bean.name);
    }
}
