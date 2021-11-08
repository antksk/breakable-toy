package com.github.antksk.breakabletoy.algo.programmers._2018._11st;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/*
1일부터 N일까지, 날짜별 주가가 순서대로 들어있는 배열이 있습니다. 이때, 주식을 구매한 후 차익을 얻기까지 날짜별로 최소 며칠씩 기다려야 하는지 알고 싶습니다.

예를 들어 N = 5일 때 1일부터 5일까지 각 날짜의 주가가 [4, 1, 4, 7, 6]인 경우,

1일에 주식을 4원에 산 후, 2일 혹은 3일에 주식을 판매하면 차익이 없으며, 4일에 주식을 판매하면 차익 3원을 얻습니다. 따라서 1일에 주식을 산 후, 차익을 얻기까지 최소 3일이 지나야 합니다.
마찬가지로 2일에 주식을 1원에 산 경우 하루가 지나면 차익을 얻으며, 3일에 주식을 4원에 산 경우에도 하루 뒤에 차익을 얻습니다. 그러나 4일, 5일에 각각 7원, 6원에 주식을 산 경우에는 차익을 얻을 수 있는 날이 없습니다.
1일부터 N일까지 날짜별로 주식의 가격이 순서대로 들어있는 배열 price가 매개변수로 주어질 때, 날짜별로 차익을 얻으려면 최소 며칠을 기다려야 하는지 알려주는 배열을 return 하도록 solution 함수를 완성해주세요. 단, N일까지
차익을 얻을 수 없는 경우에는 -1을 담으면 됩니다
 */
@Slf4j
public class Test_2 {
    public int findResult(int[] price, final int target) {
        // return Arrays.stream(price).parallel().filter(p->target < p).findAny().orElse(-1);
        for (int i = 0; i < price.length; i++) {
            if (target < price[i]) {
                return i + 1;
            }
        }
        return -1;
    }

    public int[] solution(int[] price) {
        final int PRICE_LEN = price.length;
        int[] answer = new int[PRICE_LEN];
        for (int i = 0; i < PRICE_LEN; i++) {
            int[] nextDayPrice = Arrays.copyOfRange(price, i + 1, PRICE_LEN);
            log.debug("new int[]({}), {}", nextDayPrice, price[i]);
            answer[i] = findResult(nextDayPrice, price[i]);
        }
        return answer;
    }

    @Test
    public void test() {
        log.debug("{}", solution(new int[]{4, 1, 4, 7, 6}));
        //      solution(new int[]{13,7,3,7,5,16,12});
    }
}
