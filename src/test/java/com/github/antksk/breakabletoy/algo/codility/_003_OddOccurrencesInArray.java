package com.github.antksk.breakabletoy.algo.codility;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class _003_OddOccurrencesInArray {
    static class Solution {

        public class OddOccurrences{
            private final int number;
            private final long count;
            public OddOccurrences(int number, long count){
                this.number = number;
                this.count = count;
            }

            public int getNumber(){ return number; }
            public long getCount(){ return count; }
            public boolean isDuplicateNumber(){
                return 1 < count;
            }

            public boolean isNotDuplicateNumber(){
                return false == isDuplicateNumber();
            }

            @Override
            public int hashCode() {

                return number;
            }

            @Override
            public boolean equals(Object obj) {
                if( obj instanceof OddOccurrences ){
                    OddOccurrences o = (OddOccurrences)obj;
                    return number == o.number;
                }
                return false;
            }

            @Override
            public String toString() {
                return String.format("OddOccurrences(number=%d, count=%d)", number, count);
            }
        }

        public class OddOccurrencesSet extends AbstractSet<OddOccurrences>{

            public final Set<OddOccurrences> innerSet;

            public OddOccurrencesSet(int[] array){
                innerSet = IntStream.of(array).boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .map(e->new OddOccurrences(e.getKey(), e.getValue()))
                        .collect(Collectors.toSet());
                log.debug("{}",innerSet);
            }

            @Override
            public Iterator<OddOccurrences> iterator() {
                return innerSet.iterator();
            }

            @Override
            public int size() {
                return innerSet.size();
            }
        }

        public int solution(int[] A){
            Optional<OddOccurrences> any = new OddOccurrencesSet(A)
                    .stream()
                    .filter(OddOccurrences::isNotDuplicateNumber)
                    .findAny();
            return any.isPresent() ? any.get().getNumber() : -1;
        }
    }

    class Solution2 {
        public int solution(int[] A) {
            // write your code in Java SE 8
            Map<Integer, Boolean> pairedMap = new HashMap<>();

            for(int i = 0 ; i < A.length ; i++){
                int occurredNumber = A[i];
                Boolean paired = pairedMap.get(occurredNumber);
                if(paired == null || paired == true){
                    pairedMap.put(occurredNumber, false);
                }
                else{
                    pairedMap.put(occurredNumber, true);
                }
            }

            Iterator<Integer> iterator = pairedMap.keySet().iterator();
            int minValue = Integer.MAX_VALUE;
            while(iterator.hasNext()){
                int key = iterator.next();
                Boolean paired = pairedMap.get(key);
                if(paired == false){
                    minValue = Math.min(minValue, key);
                }
            }

            return minValue;
        }
    }

    @Test
    public void test(){
       Solution2 s = new Solution2();
       log.debug("{}", s.solution(new int[]{9, 3, 9, 3, 9, 7, 9}));
    }
}
