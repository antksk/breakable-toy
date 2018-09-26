package com.github.antksk.breakabletoy.algo.programmers._2018.ya;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 두 개의 정수 A와 B가 주어진다. 우리는 A의 십진수 표현이 B의 십진수 표현 (0에서부터)에서 부분 문자열로 나타나는 위치에 관심이있다. 예를 들어
 *
 * 십진법 표현은 빅 엔디안이며 0을 선두로하는 것으로 가정됩니다 (유일한 예외는 숫자 0이며 10 진수 표현은 "0"입니다).
 *
 * 함수 작성
 *
 *
 * 두 정수 A와 B가 주어지면 B에서 A가 발생하는 가장 왼쪽 위치를 반환합니다. A에서 B가 발생하지 않으면 함수는 -1을 반환해야합니다.
 *
 * 예를 들어, A = 53이고 B = 1953786 인 경우 함수는 설명 된대로 2를 반환해야합니다.
 */
@Slf4j
public class Test_2 {
    class Solution{
        public int solution(int A, int B) {
            String strB = String.valueOf(B);

            final Pattern p = Pattern.compile(String.format("(%d)",A));
            final Matcher m = p.matcher(String.valueOf(B));
            final List<Integer> result = new ArrayList<>();

            while(m.find()){
               // System.out.println("### a : " + m.group(1) + ", " + m.groupCount());
                log.debug("s:{}, e:{} ",m.start(), m.end());
                result.add(m.start());
            }

            return result.stream().mapToInt(Integer::intValue).sum();
        }
    }

    @Test
    public void test(){
        Solution s = new Solution();

        assertThat(s.solution(53, 1953786), is(2));
        assertThat(s.solution(78, 195378678), is(11));
    }
}
