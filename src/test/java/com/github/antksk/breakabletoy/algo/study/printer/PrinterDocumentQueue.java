package com.github.antksk.breakabletoy.algo.study.printer;

import java.util.AbstractList;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

class PrinterDocumentQueue extends AbstractQueue<Document> {
    private List<Document> queue;

    public PrinterDocumentQueue(){
        queue = new ArrayList<>();
    }



    public PrinterDocumentQueue(int capacity){
        queue = new ArrayList<>(capacity);
    }

    public boolean isNotEmpty(){
        return false == isEmpty();
    }

    public Document findByMaxDocument(){
        return stream().max(Document.ascWithNo()).orElseGet(Document::empty);
    }


    public Document select(int index) {
        return queue.get(index);
    }

    @Override
    public Iterator<Document> iterator() {
        return queue.iterator();
    }


    @Override
    public boolean add(Document document) {
        return queue.add(document);
    }



    @Override
    public int size() {
        return queue.size();
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
        return queue.remove(0);
    }

    @Override
    public Document element() {
        if( isEmpty() ) throw new NoSuchElementException();
        return peek();
    }

    @Override
    public Document peek() {
        return select(0);
    }
}
