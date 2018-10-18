package com.github.antksk.breakabletoy.algo.study.printer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.IntStream;

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

        static final class PrinterDocumentManager{
            private final PrinterDocumentQueue queue;
            private final Document selectedDocument;
            private Document maxDocument;

            private PrinterDocumentManager(int[] priorities, int location){
                this.queue = genDocumentQueue(priorities);
                this.selectedDocument = queue.select(location);

                uplodateMaxDocument();
            }

            private PrinterDocumentQueue genDocumentQueue(int[] priorities) {
                return IntStream.of(priorities).mapToObj(Document::of)
                        .collect(PrinterDocumentQueue::new, PrinterDocumentQueue::add, PrinterDocumentQueue::addAll);
            }

            private boolean isPrinterDocumentQueueNotEmpty(){
                return queue.isNotEmpty();
            }

            private boolean isCurrentDocumentEqualMaxDocument(Document current){
                return maxDocument.is(current);
            }

            private boolean isCurrentDocumentEqualSelectedDocument(Document current){
                return selectedDocument.is(current);
            }

            private Document currentDocument(){
                return queue.poll();
            }

            private void postponePrintingForCurrentDocument(Document current){
                queue.offer(current);
            }

            private void uplodateMaxDocument(){
                maxDocument = queue.findByMaxDocument();;
            }

            private void process(Supplier<Integer> supplier){
                // 대기열이 비여 있지 않으면,
                while(isPrinterDocumentQueueNotEmpty()){
                    // 현재 원소를 가져와서
                    final Document current = currentDocument();

                    // 현재 대기열에 있는 MaxDocument 문서지 확인하고,
                    if( isCurrentDocumentEqualMaxDocument(current) ){
                        supplier.get();

                        uplodateMaxDocument();
                        // 선택한 문서가 대기열에서 빠진걸로 판단되면
                        if( isCurrentDocumentEqualSelectedDocument(current) ){
                            // 더 이상 대기열에서 확인하지 않음
                            break;
                        }
                    }
                    // MaxDocument 문서가 아니면 큐 대기열 마지막에 다시 저장
                    else{
                        postponePrintingForCurrentDocument(current);
                    }
                }
            }

            public static PrinterDocumentManager collectingPrimitiveData(int[] priorities, int location){
                return new PrinterDocumentManager(priorities, location);
            }

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
