package com.github.antksk.breakabletoy.algo.study.printer;

import javax.print.Doc;
import java.util.Comparator;

class Document implements Comparable<Document> {
    private final String id;
    private final int no;
    private Document(String id, int doc){
        this.id = id;
        this.no = doc;
    }

    private static Document EMPTY = new Document("", -1);

    public static Document empty(){ return EMPTY; }

    public int getNo() {
        return no;
    }

    public boolean fullEquals(Document o){
        return id.equals(o.id) && equals(o);
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Document){
            Document o = (Document)obj;
            return no == o.no;
        }
        return false;
    }

    public static Comparator<Document> ascWithNo(){
        return Comparator.comparing(Document::getNo);
    }

    @Override
    public int compareTo(Document o) {
        if(equals(o)) return 0;
        return 0 < (no - o.no) ? 1 : -1;
    }

    static class DocumentSeqGenerator {
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
            initCharIndexIter();
        }
    }

    public static Document of(int doc ){
        return new Document(DocumentSeqGenerator.nextCharIndex(), doc);
    }


    @Override
    public String toString() {
        return String.format("[%3s]%d", id, no);
    }
}
