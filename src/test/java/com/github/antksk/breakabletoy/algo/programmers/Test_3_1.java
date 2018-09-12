package com.github.antksk.breakabletoy.algo.programmers;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class Test_3_1 {
    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        int n = land.length;

        int [] land2 = new int[n*n];

        long accumulBlocks = 0;
        long totalBlocks = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                land2[n*i + j] = land[i][j];
                totalBlocks += land[i][j];
            }
        }
        log.debug("{}",land2);
        Arrays.sort(land2);

        log.debug("{}",land2);

        for(int i=0; i<n*n; i++) {
            accumulBlocks += land2[i];
            long addBlocks = (land2[i] * (long)(i+1)) - accumulBlocks;
            long delBlocks = (totalBlocks - accumulBlocks - (long)(n * n - i - 1)*land2[i]);
            long resource = addBlocks * P + delBlocks * Q;
            if(answer > resource) answer = resource;
        }

        return answer;
    }

    @Test
    public void test(){
        log.debug("{}", solution(new int[][]{{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }}, 5, 3));
    }
}
