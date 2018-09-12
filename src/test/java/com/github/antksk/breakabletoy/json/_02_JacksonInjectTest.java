package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Slf4j
public class _02_JacksonInjectTest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class TestJsonObject {
        @JacksonInject("test")
        private int id;

        private String name;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("test", id)
                    .append("name", name)
                    .build();
        }
    }

    @Test
    public void test() throws IOException {
        String json = "{\"name\":\"My bean\"}";

        InjectableValues inject = new InjectableValues.Std()
                .addValue("test", 1);
        TestJsonObject bean = new ObjectMapper().reader(inject)
                .forType(TestJsonObject.class)
                .readValue(json);

        log.debug("bean : {}", bean);

        assertEquals("My bean", bean.name);
        assertEquals(1, bean.id);
    }
}
