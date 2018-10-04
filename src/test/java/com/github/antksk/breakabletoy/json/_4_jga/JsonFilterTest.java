package com.github.antksk.breakabletoy.json._4_jga;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class JsonFilterTest {
    @JsonFilter("myFilter")
    @Value
    public class BeanWithFilter {
        private int id;
        private String name;
    }


    @Test
    public void whenSerializingUsingJsonFilter_thenCorrect() throws JsonProcessingException {
        BeanWithFilter bean = new BeanWithFilter(1, "My bean");

        FilterProvider filters = new SimpleFilterProvider().addFilter(
                "myFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name")); // {"name":"My bean"}

        String result = new ObjectMapper()
                .writer(filters)
                .writeValueAsString(bean);

        log.debug("json : {}", result);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));
    }
}
