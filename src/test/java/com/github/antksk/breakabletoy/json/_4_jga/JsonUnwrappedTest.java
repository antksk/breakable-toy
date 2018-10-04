package com.github.antksk.breakabletoy.json._4_jga;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class JsonUnwrappedTest {
    @Getter
    @AllArgsConstructor
    public static class UnwrappedUser {
        private int id;

        @JsonUnwrapped(suffix = "Name")
        private Name name;

        public static Name createName(String first, String last){
            return new Name(first, last);
        }

        @Getter
        public static final class Name {
            private final String first;
            private final String last;

            public Name(String first, String last){
                this.first = first;
                this.last = last;
            }
        }
    }


    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect() throws JsonProcessingException, ParseException {
        UnwrappedUser user = new UnwrappedUser(1, UnwrappedUser.createName("John", "Doe"));

        String result = new ObjectMapper().writeValueAsString(user);

        log.debug("json : {}", result);

        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("name")));
    }
}
