package com.github.antksk.breakabletoy.algo.programmers._2020.la;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 1분에 한 번씩 온도를 측정하는 장치를 가지고 있습니다.
 * 이 장치의 온도 센서는 100% 신뢰할 수 없으며 때로는 엄청나게 부정확한 값을 보입니다.
 * 하지만 새로운 장치를 구입할 비용이 없어 소프트웨어를 이용해 오차를 보정하기로 했습니다.
 *
 * 다음항목 중 하나라도 만족하면 오차로 판단합니다.
 *
 * 온도 < -200
 * 온도 > 200
 * 측정값 전후 2분이내에 측정한 온도 모두가 3도 이상으로 차이가 날 경우
 * 입력형식
 *
 * 입력값은 최근 몇 분 동안 측정된 온도를 시간순으로 주어집니다.
 * 각각의 온도는 공백(Space)으로 구분되어 집니다.
 *
 * 출력형식
 *
 * 오차 범위 내의 유효한 측정값의 평균을 계산하여 반환합니다.
 * 만약 소수점이 존재한다면 버림하고 정수만 반환합니다.
 * 유효한 측정값이 없으면 ERROR 를 반환합니다.
 *
 *
 * 10 12	11
 * 10 13	ERROR
 * 10 13 9	9
 * 1 2 3 100 100 1 2	29
 * 10 16 13 12 15 14 12	13
 */
@Slf4j
public class Test_2 {
    public int solution(String s) {
        int[] c = Stream.of(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> a = new ArrayList<>(c.length);

        for(int i = 0; c.length > i; ++i){
            int prev = (0 == i) ? 0 : i-1;
            System.out.println( c[i % 3] );
            if( 3 >  c[i] - c[prev]
                &&  c[i] > -200
                &&  c[i] < 200 ){
                a.add(c[i]);
            }
        }
        System.out.println(a);
        return a.isEmpty() || a.size() == 1 ? -1 : (int) Math.floor(a.stream().mapToInt(x->x).average().orElse(0.0));
        // return (int) Math.floor(Stream.of(s.split(" ")).mapToInt(Integer::parseInt).average().orElse(0.0));
    }

    @Test
    public void test() {
//        log.debug("{}", solution("10 12"));
//        log.debug("{}", solution("10 13"));
//        log.debug("{}", solution("10 13 9"));
        log.debug("{}", solution("1 2 3 100 100 1 2"));
//        log.debug("{}", solution("10 16 13 12 15 14 12"));
    }
}
