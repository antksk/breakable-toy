package com.github.antksk.breakabletoy.algo.study.printer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 코딩 연습 > 스택/큐 > 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=java#
 */
@Slf4j
public class PrinterTest {

    static class Solution {

        public int solution(int[] priorities, int location) {
            final PrinterDocumentQueue documents = genDocumentQueue(priorities);
            final Document selectedDocument = documents.get(location);

            int result = 0;


            Document max = documents.max();

            // 대기열이 비여 있지 않으면,
            while(documents.isNotEmpty()){
                // 첫번째 원소를 가져와서
                Document current = documents.remove();

                // 현재 대기열에 있는 max 문서지 확인하고,
                if( max.fullEquals(current) ){
                    ++result;

                    max = documents.max();
                    // 선택한 문서가 대기열에서 빠진걸로 판단되면
                    if( selectedDocument.fullEquals(current) ){
                        // 더 이상 대기열에서 확인하지 않음
                        break;
                    }
                }
                // max 문서가 아니면 큐 대기열 마지막에 다시 저장
                else{
                    documents.offer(current);
                }

                // log.debug("c: {}, m: {}", current, max);
            }

            return result;
        }

        private PrinterDocumentQueue genDocumentQueue(int[] priorities) {
            return IntStream.of(priorities).mapToObj(Document::of)
                    .collect(PrinterDocumentQueue::new, PrinterDocumentQueue::add, PrinterDocumentQueue::addAll);
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
