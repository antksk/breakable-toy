package com.github.antksk.breakabletoy.json._3_jptha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@Slf4j
public class JsonTypeInfoTest {

    @ToString
    public static class Zoo {
        @Getter
        private final Animal animal;

        @JsonCreator
        public Zoo(@JsonProperty("animal") Animal animal){
            this.animal = animal;
        }



        @JsonTypeInfo(
                use = JsonTypeInfo.Id.NAME,
                include = JsonTypeInfo.As.PROPERTY,
                property = "type"
        )
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Dog.class, name = Dog.NAME),
                @JsonSubTypes.Type(value = Cat.class, name = Cat.NAME)
        })
        @AllArgsConstructor
        @Getter
        public static abstract  class Animal {
            @Getter
            protected final String name;
        }


        @JsonTypeName(Dog.NAME)
        public static class Dog extends Animal {
            public static final String NAME = "dog";
            public double barkVolume;

            @JsonCreator
            public Dog(@JsonProperty("name") String name){
                super(name);
            }
        }

        @ToString
        @JsonTypeName(Cat.NAME)
        public static class Cat extends Animal {
            public static final String NAME = "cat";

            @JsonCreator
            public Cat(@JsonProperty("name") String name){
                super(name);
            }
            boolean likesCream;
            public int lives;
        }
    }

    @Test
    public void whenSerializingPolymorphic_thenCorrect()
            throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("lacy");
        Zoo zoo = new Zoo(dog);

        String result = new ObjectMapper()
                .writeValueAsString(zoo);

        log.debug("result json : {}", result);

        assertThat(result, containsString("type"));
        assertThat(result, containsString("dog"));
    }

    @Test
    public void whenDeserializingPolymorphic_thenCorrect()
            throws IOException {
        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

        Zoo zoo = new ObjectMapper()
                .readerFor(Zoo.class)
                .readValue(json);

        log.debug("bean : {}", zoo);

        assertEquals("lacy", zoo.getAnimal().getName());
        // assertEquals(Zoo.Cat.class, zoo.getAnimal().getClass());
    }
}
