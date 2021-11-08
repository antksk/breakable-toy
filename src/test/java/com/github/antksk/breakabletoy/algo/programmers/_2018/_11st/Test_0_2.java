package com.github.antksk.breakabletoy.algo.programmers._2018._11st;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/*
이 문제에는 표준 입력으로 두 개의 정수 n과 m이 주어집니다.
별(*) 문자를 이용해 가로의 길이가 n, 세로의 길이가 m인 직사각형 형태를 출력해보세요.
 */

@Slf4j
public class Test_0_2 {
    public void solution(int a, int b) {
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void test() {
        solution(5, 3);
    }
}
