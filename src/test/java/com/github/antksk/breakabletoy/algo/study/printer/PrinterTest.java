package com.github.antksk.breakabletoy.algo.study.printer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 코딩 연습 > 스택/큐 > 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=java#
 */
@Slf4j
public class PrinterTest {

    static class Solution {

        private int answer;

        public Solution(){
            initAnswer();
        }

        private void initAnswer(){
            answer = 0;
        }

        private int incrementAndGet(){
            return ++answer;
        }

        private int getAnswer(){
            return answer;
        }

        public int solution(int[] priorities, int location) {
            initAnswer(); // 해답에 대한 초기화 진행

            PrinterDocumentManager
                    .collectingPrimitiveData(priorities, location) // 테스트 요청 정보를 받아서
                    .process(this::incrementAndGet); // 결과 값을 계산하여, 해답을 찾은 후

            return getAnswer(); // 해답을 리턴한다.
        }
    }

    @Test
    public void 문서_번호_자동_생성(){
        for( int i = 0; 26 * 4 > i; ++i )
            log.debug("{}", Document.of(i));
    }

    @Test
    public void 문제_테스트(){
        Solution s = new Solution();
        log.debug("{}", s.solution(new int[]{2,1,3,2}, 2));
        log.debug("{}", s.solution(new int[]{1,1,9,1,1,1}, 0));
        log.debug("{}", s.solution(new int[]{0,1,2,3,4,5,6,7,8,9}, 0));
    }

}
