package com.github.antksk.breakabletoy.algo.programmers._2018.ya;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * N 개의 정수로 구성된 비어 있지 않은 배열 A가 주어진다.
 * <p>
 * 배열 A에서 단일 스왑 연산을 수행 할 수 있습니다.이 연산은 0 <I <J <N과 같이 두 개의 인덱스 I와 J를 사용하고 A (I)와 A (J)의 값을 교환합니다.
 * <p>
 * 목표는 배열 A가 스왑 오퍼레이션에서 최대 수행으로 비 감수 순서로 정렬 될 수 있는지 확인하는 것입니다.
 * <p>
 * 예를 들어, 배열 A를 다음과 같이 고려하십시오.
 * <p>
 * <p>
 * 값 A (1) 및 A (3)을 교환 한 후, 배열 (1,3,3,5,7)을 비 - 차순으로 정렬한다.
 */
@Slf4j
public class Test_3 {
    public static class Solution {
        public boolean solution(int[] A) {
            log.debug("A=[{}]", A);
            final int N = A.length;
            boolean result = false;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (A[i] > A[j]) {
                        result = true;
                        usingTempOperatorsVersionSwap(A, i, j);
                    }
                }
            }
            log.debug("A=[{}]", A);
            return result;
        }

        // temp 변수 없이 정렬 하는 방식
        private static void usingArithmeticOperatorsVersionSwap(int[] A, int i, int j) {
            A[i] = A[i] + A[j];  // x는 15
            A[j] = A[i] - A[j];  // y는 10변경 ( x --> y )
            A[i] = A[i] - A[j];  // x는 5변경 ( y --> x )
            log.debug("x = {}, y = {}", A[i], A[j]);
        }

        // temp 변수를 사용하는 방식
        public static void usingTempOperatorsVersionSwap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        s.solution(new int[]{1, 5, 3, 3, 7});
    }
}
