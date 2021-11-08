package com.github.antksk.breakabletoy.ddd.pizza.c;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.Builder;
import lombok.Singular;

import static java.util.stream.Collectors.toList;

public class Step3_Test {
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
        System.out.println(solution(new int[]{1999,1010101,99999, 100000}, 2));
//        solution(new int[]{3, 5, 1, 3, 9, 8}, 4);
//        solution(new int[]{3, 5, 1, 3, 9, 8}, 4);
    }
    static int solution(int[] A, int K) {

        if (K >= A.length)
            return 0;

        if( K + K <= A.length)
            IntStream.range(K, K+K).forEach(e-> A[e] = -1);
        else
            IntStream.range(0, K).forEach(e-> A[e] = -1);

        List<Integer> collect = IntStream.of(A).filter(e -> 0 < e).boxed().collect(toList());

        int max = Collections.max(collect);;
        int min = Collections.min(collect);;

//        System.out.println(l + " : " + max + ", " + min);

        return max - min;
    }
}
