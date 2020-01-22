package com.github.antksk.breakabletoy.code.test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumTest {
    public interface EnumCode {
        String getCode();
    }

    @Getter
    public enum TestEnum1 implements EnumCode {
        T1("01"),
        T2("02");
        private final String code;

        TestEnum1(String code) {
            this.code = code;
        }
    }

    @Getter
    public enum TestEnum2 implements EnumCode {
        T1("01"),
        T2("02");
        private final String code;

        TestEnum2(String code) {
            this.code = code;
        }
    }

    @Test
    public void test() {
        final Set<EnumCode> testSet = ImmutableSet.of(
            TestEnum1.T1,
            TestEnum2.T1
        );
//        log.debug("{}", testSet);
//
//        log.debug("{}", Strings.repeat("a", 10) );
//        log.debug("{}, {}", TestEnum1.T1 == TestEnum1.T2, TestEnum1.T1 == TestEnum1.T1);
        log.debug("Enum.valueOf : {}, Enums.getField : {}", Enum.valueOf(TestEnum1.class, "T1"));
    }

    @Test
    public void test2(){
        final String collect = Arrays.stream(StringUtils.leftPad("12341", 6,'0').split(""))
                                     .collect(Collectors.joining("/"));
        System.out.println("aa = " + collect);
    }


}
