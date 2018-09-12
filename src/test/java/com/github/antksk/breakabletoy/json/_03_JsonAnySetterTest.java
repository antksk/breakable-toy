package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Slf4j
public class _03_JsonAnySetterTest {

    public static final class DummyPropertiesMap extends AbstractMap<String,String>{
        private Map<String,String> map;

        public DummyPropertiesMap(){
            map = new HashMap<>();
        }

        @Override
        public String get(Object key) {
            return map.get(key);
        }

        @Override
        public String put(String key, String value) {
            return map.put(key, value);
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return map.entrySet();
        }
    }

    @Getter
    public static final class TestJsonObject {

        private String name;

        private DummyPropertiesMap properties;

        public TestJsonObject(){
            properties = new DummyPropertiesMap();
        }

        @JsonAnySetter
        public void add(String key, String value) {
            properties.put(key, value);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("name", name)
                    .append("properties", properties)
                    .build();
        }
    }

    @Test
    public void test()
            throws IOException {
        String json
                = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";

        TestJsonObject bean = new ObjectMapper()
                .readerFor(TestJsonObject.class)
                .readValue(json);

        log.debug("{}", bean);

        assertEquals("My bean", bean.name);
        assertEquals("val2", bean.getProperties().get("attr2"));
    }
}
