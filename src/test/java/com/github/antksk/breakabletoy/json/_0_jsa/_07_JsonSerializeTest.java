package com.github.antksk.breakabletoy.json._0_jsa;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;



/**
 * @JsonSerialize 사용자 정의 serializer를 선언하여 연결 시킴
 *  - 사용자 정의 serializer는 JsonSerializer 추상 클래스를 상속 받아서 구현 해야 함
 *  - serialize 첫번째 파라미터로 전달된 Object value 값으로 @JsonSerialize 필드에 지정된 필드 형식을 확인 가능함
 **/
@Slf4j
public class _07_JsonSerializeTest {

    @AllArgsConstructor
    @Getter
    public class TestJsonObject {

        private String name;

        @JsonSerialize(using = CustomDateSerializer.class)
        private Date eventDate;
    }

    private static class CustomDateSerializer extends JsonSerializer {
        public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(formatter.format(value));
        }
    }


    @Test
    public void test() throws ParseException, JsonProcessingException {
        String toParse = "20-12-2014 02:30:00";
        Date date = CustomDateSerializer.formatter.parse(toParse);
        TestJsonObject event = new TestJsonObject("party", date);

        String result = new ObjectMapper().writeValueAsString(event);

        log.debug("{}",result);

        assertThat(result, containsString(toParse));
    }
}
