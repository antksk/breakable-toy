package com.github.antksk.breakabletoy.algo.study;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/*
This is a demo task.

Write a function:

class Solution { public int solution(int[] A);
}

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
*/
@Slf4j
public class TheSmallestPositiveIntegerTest {
    class Solution {
        public int solution(int[] A){
            Arrays.sort(A);
            log.debug("{}", A);

            final int len = A.length;
            for (int i = 1; i < len; i++) {
                final int small = A[i-1];
                final int large = A[i];
                final int gap = Math.abs(small - large);
                if( 1 < gap ){
                    return smalllest(small + 1);
                }
            }

            return smalllest(A[len-1] + 1);
        }

        public int smalllest(int small){
            return 0 < small ? small : 1;
        }
    }

    @Test
    public void test(){
        Solution s = new Solution();
        assertThat(s.solution(new int[]{1,3,6,4,1,2}), is(5));
        assertThat(s.solution(new int[]{1,2,3}), is(4));
        assertThat(s.solution(new int[]{-1,-3}), is(1));
        assertThat(s.solution(new int[]{-1,-2, -3}), is(1));
        assertThat(s.solution(new int[]{-1_000_000, 2, 1_000_000}), is(1));
    }
}
