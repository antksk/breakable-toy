package com.github.antksk.breakabletoy.ddd.pizza.c;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.Builder;
import lombok.Singular;

public class Step2_Test {
    @Builder
    static final class FormatString{
        private final String msg;
        @Singular
        private final List<Object> ages;

        public FormatString(String msg, List<Object> ages) {
            this.msg = msg;
            this.ages = ages;
        }

        public static FormatStringBuilder fm(String msg) {
            return FormatString.builder().msg(msg);
        }

        public String toMsg(){
            return String.format(msg, ages.toArray());
        }

        public void display(){
            System.out.println(toMsg());
        }
    }


    @Test
    public void test() {
        // solution(new int[]{3,2,1,1,2,3,1});
//        solution(new int[]{4,1,4,1});
        solution(new int[]{3,3,3});
    }
    static int solution(int[] A) {
        final int a = (int)Math.round(IntStream.of(A).average().getAsDouble());
        return IntStream.of(A)
                        .map(e -> Math.abs(a - e))
                        .sum();
    }
}
