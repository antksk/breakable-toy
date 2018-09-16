package com.github.antksk.breakabletoy.json._1_jda;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Slf4j
public class _05_JsonDeserializeTest {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static  class TestJsonObject {

        private String name;

        @JsonDeserialize(using = CustomDateDeserializer.class)
        private Date eventDate;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("name", name)
                    .append("eventDate", eventDate)
                    .build();
        }

    }

    public static class CustomDateDeserializer extends StdDeserializer<Date> {

        public final static SimpleDateFormat formatter
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");


        public CustomDateDeserializer() {
            this(null);
        }

        public CustomDateDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Date deserialize(
                JsonParser jsonparser, DeserializationContext context)
                throws IOException {

            String date = jsonparser.getText();
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test() throws IOException {
        String json
                = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        TestJsonObject bean = new ObjectMapper()
                .readerFor(TestJsonObject.class)
                .readValue(json);

        log.debug("{}", bean);

        assertEquals(
                "20-12-2014 02:30:00", CustomDateDeserializer.formatter.format(bean.eventDate));
    }

}
