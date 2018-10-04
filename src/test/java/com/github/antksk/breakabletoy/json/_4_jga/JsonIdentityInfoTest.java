package com.github.antksk.breakabletoy.json._4_jga;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class JsonIdentityInfoTest {


    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    public static class User {
        @Getter
        private final int id;
        @Getter
        private final String name;

        private final List<Item> items;

        public User(int id, String name){
            this.id = id;
            this.name = name;
            this.items = Lists.newArrayList();
        }


        public boolean addItem(int id, String itemName){
            return items.add( new Item(id, itemName, this) );
        }

        public Item getItem(int index){
            return items.get(index);
        }
    }

    @Getter
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    public static final class Item {
        private final int id;
        private final String itemName;

        private final User owner;

        public Item(int id, String itemName, User user) {
            this.id = id;
            this.itemName = itemName;
            this.owner = user;
        }
    }

    @Test
    public void whenSerializingUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
        JsonBackReferenceAndJsonManagedReferenceTest.User user = new JsonBackReferenceAndJsonManagedReferenceTest.User(1, "John");
        user.addItem(100, "book");
        user.addItem(101, "pen");

        JsonBackReferenceAndJsonManagedReferenceTest.Item fristItem = user.getItem(0);
        String result = new ObjectMapper().writeValueAsString(fristItem);

        log.debug("json : {}", result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));
    }

}
