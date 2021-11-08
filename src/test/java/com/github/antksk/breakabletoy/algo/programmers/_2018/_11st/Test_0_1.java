package com.github.antksk.breakabletoy.algo.programmers._2018._11st;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/*
길이가 n인 배열에 1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는지를 확인하려고 합니다.
1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는 경우 true를, 아닌 경우 false를 반환하도록 함수 solution을 완성해주세요.

제한사항
배열의 길이는 10만 이하입니다.
배열의 원소는 10만 이하의 자연수입니다.
 */

@Slf4j
public class Test_0_1 {
    public boolean solution(int[] arr) {
/*
        final Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if( result.containsKey(key) ){
                result.put(key, result.get(key) + 1);
            }else{
                result.put(key, 1);
            }
        }

        log.debug("{}", result);


*/
        Map<Integer, Long> result = Arrays.stream(arr).boxed().collect(groupingBy(identity(), counting()));
        for (Map.Entry<Integer, Long> e : result.entrySet()) {
            if (1 < e.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        log.debug("{}", solution(new int[]{4, 1, 3}));
    }
}
