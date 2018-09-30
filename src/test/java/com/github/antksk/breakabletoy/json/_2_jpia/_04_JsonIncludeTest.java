package com.github.antksk.breakabletoy.json._2_jpia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
public class _04_JsonIncludeTest {

    /*
        - Include.VALUE :
    - Include.NON_NULL :
    - Include.NON_ABSENT :
    - Include.NON_EMPTY :
    - Include.NON_DEFAULT :
    - Include.CUSTOM :
    - Include.USE_DEFAULTS :
     */

    interface TestJsonObject{
        int getIntValue();
        String getStrValue();
        Optional<String> getOptionalStrValue();
        List<Integer> getIntListValue();
    }

    @Getter
    private static abstract class AbstractTestJsonObject implements TestJsonObject{
        protected int intValue;
        protected String strValue;
        protected Optional<String> optionalStrValue;
        protected List<Integer> intListValue;

        public enum Keys{
            VALUE,
            EMPTY_OR_NULL,
            DEFAULT;
        }

        public static final String VALUE = "VALUE";
        public static final String EMPTY_OR_NULL = "EMPTY_OR_NULL";
        public static final String DEFAULT = "DEFAULT";

        @JsonIgnore
        private final Map<Keys,Runnable> initMap;


        protected AbstractTestJsonObject(){
            initMap = ImmutableMap.<Keys,Runnable>builder()
                    .put(Keys.VALUE, this::initValue)
                    .put(Keys.EMPTY_OR_NULL, this::initEmptyOrNull)
                    .put(Keys.DEFAULT, this::initDefault)
                    .build();
        }

        public AbstractTestJsonObject init(Keys initKey){

            if( initMap.containsKey(initKey) ){
                initMap.get(initKey).run();
            }
            return this;
        }

        private void initValue(){
            intValue = 10;
            strValue = "test";
            optionalStrValue = Optional.of("optional test");
            intListValue = Arrays.asList(1,2,3,4,5);
        }

        private void initEmptyOrNull(){
            intValue = 0;
            strValue = null;
            optionalStrValue = Optional.empty();
            intListValue = null;
        }


        private void initDefault(){
            intValue = 0;
            strValue = "";
            optionalStrValue = Optional.empty();
            intListValue = Lists.emptyList();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("intValue", intValue)
                    .append("strValue", strValue)
                    .append("optionalStrValue", optionalStrValue)
                    .append("intListValue", intListValue)
                    .build();
        }
    }

    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public static final class TestJsonObjectAlways extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectAlways(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static final class TestJsonObjectNonNull extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectNonNull(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public static final class TestJsonObjectNonAbsent extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectNonAbsent(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static final class TestJsonObjectNonEmpty extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectNonEmpty(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public static final class TestJsonObjectNonDefault extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectNonDefault(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    public static final class TestJsonObjectCustom extends AbstractTestJsonObject implements TestJsonObject{

        public static class PhoneFilter {
            private static Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

            @Override
            public boolean equals(Object obj) {

                if (obj == null || !(obj instanceof String)) {
                    return false;
                }
                //phone must match the regex pattern
                boolean result = !phonePattern.matcher(obj.toString()).matches();
                log.debug("##### PhoneFilter.equals -> obj : {}, {}", obj, result);
                return result;
            }
        }

        @Getter
        // filter 대상이 collection 타입인 경우 content 키워드를 사용해야 함
        @JsonInclude(content=JsonInclude.Include.CUSTOM, contentFilter = PhoneFilter.class)
        private Map<String,String> phones = new ImmutableMap.Builder<String,String>()
                .put("Cell", "111-111-1111")
                .put("Work", "(222) 222 2222")
                .build();


        public static class DateOfBirthFilter {

            @Override
            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof Date)) {
                    return false;
                }

                //date should be in the past
                Date date = (Date) obj;
                boolean result = !date.before(new Date());
                log.debug("##### DateOfBirthFilter.equals -> obj : {}, {}", obj, result);
                return result;
            }
        }

        @Getter
        @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = DateOfBirthFilter.class)
        private Date dateOfBirth = Date.from(ZonedDateTime.now().plusDays(1).toInstant());


        public TestJsonObjectCustom(Keys initKey){
            super();
            init(initKey);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NoArgsConstructor
    public static final class TestJsonObjectUseDefaults extends AbstractTestJsonObject implements TestJsonObject{

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> phones;

        @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
        public List<String> getPhones() {
            return phones;
        }

        public TestJsonObjectUseDefaults(Keys initKey){
            super();
            init(initKey);

        }

        @Override
        public AbstractTestJsonObject init(Keys initKey) {
            AbstractTestJsonObject init = super.init(initKey);
            phones = Lists.emptyList(); // USE_DEFAULTS에 의해서 기본 설정된 초기값을 사용함
            return init;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


    public void displayResult(TestJsonObject obj) {
        try {
            log.debug("\n[{}]\n" +
                    "\t - OBJ : {}\n" +
                    "\t - JSON : {}\n", obj.getClass().getSimpleName(), obj, getJsonResult(obj));
        } catch (JsonProcessingException e) {
            log.error("{}",e.getMessage());
        }
    }

    private String getJsonResult(TestJsonObject obj) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .writeValueAsString(obj);
    }

    @Test
    public void test(){


//        Arrays.asList(AbstractTestJsonObject.VALUE, AbstractTestJsonObject.DEFAULT, AbstractTestJsonObject.EMPTY_OR_NULL)
//                .forEach(e->{
//                    log.info("### {}", e);
//                    displayResult(new TestJsonObjectAlways(e));
//                    displayResult(new TestJsonObjectNonNull(e));
//                    displayResult(new TestJsonObjectNonAbsent(e));
//                    displayResult(new TestJsonObjectNonEmpty(e));
//                    displayResult(new TestJsonObjectNonDefault(e));
//                    displayResult(new TestJsonObjectCustom(e));
//                    displayResult(new TestJsonObjectUseDefaults(e));
//                });

        final AbstractTestJsonObject.Keys[] keys = AbstractTestJsonObject.Keys.values();
        Arrays.asList(
//                new TestJsonObjectAlways()
//                , new TestJsonObjectNonNull()
//                , new TestJsonObjectNonAbsent()
//                , new TestJsonObjectNonEmpty()
//                , new TestJsonObjectNonDefault()
//                , new TestJsonObjectCustom()
                 new TestJsonObjectUseDefaults()
        )
                .forEach(o->{

                  for(AbstractTestJsonObject.Keys key : keys)
                      displayResult(o.init(key));

        });
    }


}
