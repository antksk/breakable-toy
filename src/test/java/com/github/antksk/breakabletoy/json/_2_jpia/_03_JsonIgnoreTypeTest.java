package com.github.antksk.breakabletoy.json._2_jpia;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@Slf4j
public class _03_JsonIgnoreTypeTest {

    @Getter
    @AllArgsConstructor
    public static final class User {
        private int id;
        private Name name;

        public static Name name(String first, String last){
            return new Name(first, last);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", id)
                    .append("name", name)
                    .build();
        }

        @JsonIgnoreType
        @Getter
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @ToString
        public static final class Name {
            private String first;
            private String last;


        }
    }


    @Test
    public void test()
            throws JsonProcessingException {

        User user = new User(1, User.name("John", "Doe"));

        String result = new ObjectMapper()
                .writeValueAsString(user);

        log.debug("result json : {}", result);
        log.debug("bean : {}", user);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
        assertThat(result, not(containsString("John")));
    }
}
