package com.github.antksk.breakabletoy.json._4_jga;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@Slf4j
public class JsonViewTest {
    // 마킹용 클래스
    public static final class Views {
        public static class Public {}
        public static class Internal {}
    }

    @Getter
    @AllArgsConstructor
    public static final class Item {
        @JsonView(Views.Public.class)
        private int id;

        @JsonView(Views.Public.class)
        private String itemName;

        @JsonView(Views.Internal.class)
        private String ownerName;
    }

    @Test
    public void whenSerializingUsingJsonView_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(2, "book", "John");

        String result = new ObjectMapper()
                .writerWithView(Views.Public.class) // Views.Public으로 JsonView가 설정된 필드들에 대해서 직렬화를 잔행함
                .writeValueAsString(item);

        log.debug("json : {}", result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("2"));
        assertThat(result, not(containsString("John")));
    }
}
