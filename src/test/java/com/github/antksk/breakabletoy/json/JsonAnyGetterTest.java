package com.github.antksk.breakabletoy.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @JsonAnyGetter
 *  Map.entrySet을 기준으로 key, value 값을 json으로 생성함
 * ref :
 *  com.fasterxml.jackson.databind.ser.std.AnyGetterWriter
 *  com.fasterxml.jackson.databind.ser.std.MapSerializer
 */

@Slf4j
public class JsonAnyGetterTest {

    // custom map
    static class ExtendableBeanProperties extends AbstractMap<String, String>{

        private Map<String, String> properties;

        public ExtendableBeanProperties(){
            properties = new HashMap<>();
        }

        @Override
        public String put(String key, String value) {
            return properties.put(key, value);
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return properties.entrySet();
        }
    }


    static class ExtendableBean {
        public String name;
        private ExtendableBeanProperties properties;

        public ExtendableBean(String name) {
            this.name = name;
            this.properties = new ExtendableBeanProperties();
        }

        @JsonAnyGetter
        public ExtendableBeanProperties getProperties() {
            return properties;
        }

        public void add(String attr, String value) {
            properties.put(attr,value);
        }
    }

    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect()
            throws JsonProcessingException {

        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);

        log.debug("result json : {}", result);

        assertThat(result, containsString("attr1"));
        assertThat(result, containsString("val1"));
    }

}
