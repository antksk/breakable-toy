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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @JsonInclude(JsonInclude.Include.CUSTOM)
    public static final class TestJsonObjectCustom extends AbstractTestJsonObject implements TestJsonObject{
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
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    public static final class TestJsonObjectUseDefaults extends AbstractTestJsonObject implements TestJsonObject{
        public TestJsonObjectUseDefaults(Keys initKey){
            super();
            init(initKey);
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
        Arrays.asList(new TestJsonObjectAlways(),
                new TestJsonObjectNonNull(),
                new TestJsonObjectNonAbsent(),
                new TestJsonObjectNonEmpty(),
                new TestJsonObjectNonDefault(),
                new TestJsonObjectCustom(),
                new TestJsonObjectUseDefaults()).forEach(o->{

                  for(AbstractTestJsonObject.Keys key : keys)
                      displayResult(o.init(key));

        });
    }
}
