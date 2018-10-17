package com.github.antksk.breakabletoy.algo.programmers._2018;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * 코딩 연습 > 스택/큐 > 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=java#
 */
@Slf4j
public class Printer {

    static class PrinterDocumentQueue extends AbstractList<Document> implements Queue<Document> {
        private List<Document> queue;

        public PrinterDocumentQueue(){
            queue = new ArrayList<>();
        }

        public PrinterDocumentQueue(int capacity){
            queue = new ArrayList<>(capacity);
        }

        @Override
        public void add(int index, Document document) {
            queue.add(index, document);
        }

        @Override
        public Document get(int index) {
            return queue.get(index);
        }

        @Override
        public int size() {
            return queue.size();
        }

        @Override
        public Document remove(int index) {
            return queue.remove(index);
        }

        // 대기열 맨 우측에 문서 추가
        @Override
        public boolean offer(Document document) {
            return add(document);
        }

        // 맨 좌측에서 원소 제거
        @Override
        public Document remove() {
            if( isEmpty() ) throw new NoSuchElementException();
            return poll();
        }

        @Override
        public Document poll() {
            return remove();
        }

        @Override
        public Document element() {
            if( isEmpty() ) throw new NoSuchElementException();
            return peek();
        }

        @Override
        public Document peek() {
            return get(0);
        }
    }

    static class Solution {

        public int solution(int[] priorities, int location) {
            PrinterDocumentQueue documents = genDocumentQueue(priorities);

            Document document = documents.get(location);


            int answer = 0;
            return answer;
        }

        private PrinterDocumentQueue genDocumentQueue(int[] priorities) {
            return IntStream.of(priorities).mapToObj(Document::of)
                    .collect(PrinterDocumentQueue::new, PrinterDocumentQueue::add, PrinterDocumentQueue::addAll);
        }
    }

    private static class Document implements Comparable<Document> {
        private final String id;
        private final int index;
        private final int no;
        private Document(String id, int doc){
            this.index = DocumentSeqGenerator.nextIndex();
            this.id = id;
            this.no = doc;
        }

        @Override
        public boolean equals(Object obj) {
            if( obj instanceof Document ){
                Document o = (Document)obj;
                return o.no == o.no;
            }
            return false;
        }

        @Override
        public int compareTo(Document o) {
            if(equals(o)) return 0;
            return 0 < (no - o.no) ? 1 : -1;
        }

        static class DocumentSeqGenerator {
            private static int indexIter;

            public static void initIndexIter(){
                indexIter = 1;
            }

            public static int nextIndex(){
                return indexIter++;
            }

            private static char charIndexIter;
            private static String charIndexBase;

            public static void initCharIndexIter(){
                charIndexIter = 'A';
                charIndexBase = "";
            }

            public static String nextCharIndex(){
                if( 'Z' < charIndexIter) {
                    charIndexIter = 'A';
                    charIndexBase += charIndexIter;
                }
                return charIndexBase + (charIndexIter++);
            }

            static{
                initIndexIter();
                initCharIndexIter();
            }
        }

        public static Document of( int doc ){
            return new Document(DocumentSeqGenerator.nextCharIndex(), doc);
        }


        @Override
        public String toString() {
            return String.format("[%3s](%3d)%d", id, index, no);
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
    }
}
