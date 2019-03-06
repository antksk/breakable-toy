package com.github.antksk.breakabletoy.algo.study.printer;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BinaryNumberOfRepetitionsTest {
    @Test
    public void __0이_아닌_숫자가_입력으로_들어오면_입력된_수가_홀수이면_minus1을_하고_짝수이면_2로_나누어서_0이_될때까지_반복_회수를_리턴하는_함수() {
        assertThat(new BinaryNumberOfRepetitions("11100").count(),is(7));
        assertThat(new BinaryNumberOfRepetitions("0").count(),is(0));
    }

    @Test
    public void speed_test1(){
        assertThat(new BinaryNumberOfRepetitions(Integer.toBinaryString(Integer.MAX_VALUE)).count(),is(61));
    }

    @Test
    public void speed_test2(){
       assertThat(new BinaryNumberOfRepetitions2(Integer.toBinaryString(Integer.MAX_VALUE)).count(),is(61));
    }

    public interface RepetitionsCountable {
        int count();
    }

    private class BinaryNumberOfRepetitions implements RepetitionsCountable {
        public static final int RADIX_BINARY_NUMBER = 2;
        private final int number;

        public BinaryNumberOfRepetitions(final String binaryNumber) {
            this.number = Integer.parseInt(binaryNumber, RADIX_BINARY_NUMBER);
        }

        /**
         * 지정된 숫자에 대한 분할 전략(divid strategy)을 지정하는 메서드
         * @param target
         * @return
         */
        protected int divider(int target){
            return isEvenNumber(target) ? (target / 2) : target - 1;
        }

        public boolean isEvenNumber(int number){
            return (number % 2) == 0;
        }

        public void repeat(Consumer<Integer> consumer){
            for(int i = number; 0 < i; i = divider(i)){
               consumer.accept(i);
            }
        }

        @Override
        public int count() {
            int result = 0;
            for(int i = number; 0 < i; i = divider(i),  ++result);
            return result;
        }
    }

    public class BinaryNumberOfRepetitions2 implements RepetitionsCountable {
        private final String binaryNumber;

        public BinaryNumberOfRepetitions2(final String binaryNumber){
            this.binaryNumber = binaryNumber;
        }

        // intstream을 두번 생성하기 때문에 실제 동작 느림
        @Override
        public int count() {
            final long zeroCount = binaryNumber.chars().filter(c->c == '0').count();
            final long oneCount = binaryNumber.chars().filter(c->c == '1').count();
            return (int)(zeroCount + (oneCount * 2) - 1);
        }
    }

  }
