package com.github.antksk.breakabletoy.ddd.pizza.c;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import lombok.Builder;
import lombok.Singular;

public class Step1_Test {
    @Builder
    static final class FormatString{
        private final String msg;
        @Singular
        private final List<Object> ages;

        public FormatString(String msg, List<Object> ages) {
            this.msg = msg;
            this.ages = ages;
        }

        public static FormatString.FormatStringBuilder fm(String msg) {
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
//        solution("23");
        System.out.println(solution("99999"));
    }
    static int solution(String S) {
        Set<String> r = new HashSet<>();
        String[] split = S.split("");;
        int l = split.length;
        for( int i = 0; l > i; ++i){
            String t = split[i];
            for(int n = 0; 9 >= n; ++n){
                split[i] = "" + n;
                String cn = String.join("", split);
                System.out.println(cn + "  :  " + (Integer.parseInt(cn) % 3) );
                if (0 == Integer.parseInt(cn) % 3 ){
                    r.add(cn);
                }
            }
            split[i] = t;
        }

        return r.size();
    }
}
