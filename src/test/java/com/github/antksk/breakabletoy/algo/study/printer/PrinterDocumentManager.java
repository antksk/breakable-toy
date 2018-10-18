package com.github.antksk.breakabletoy.algo.study.printer;

import java.util.function.Supplier;
import java.util.stream.IntStream;

final class PrinterDocumentManager {
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

    public void process(Supplier<Integer> supplier){
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
