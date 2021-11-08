package com.github.antksk.breakabletoy.algo.codility;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class _002_CyclicRotation {
    class Solution {
        public int[] solution(int[] A, int K) {
            final int aLength = A.length;
            final int[] result = new int[aLength];
            for (int i = 0; i < aLength; i++) {
                result[(i + K) % aLength] = A[i];
            }
            return result;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        log.debug("{}", s.solution(new int[]{3, 8, 9, 7, 6}, 3));
        log.debug("{}", s.solution(new int[]{0, 0, 0}, 1));
        log.debug("{}", s.solution(new int[]{1, 2, 3, 4}, 4));
    }
}
