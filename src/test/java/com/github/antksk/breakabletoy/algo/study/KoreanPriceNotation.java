package com.github.antksk.breakabletoy.algo.study;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.RandomAccess;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Slf4j
public class KoreanPriceNotation {

    static class KoreanPriceList extends AbstractList<KoreanPrice> implements RandomAccess {
        private final List<KoreanPrice> koreanPriceList;

        private List<KoreanPrice> partition(List<Integer> list, final int L) {
            List<KoreanPrice> result = new ArrayList();
            final int N = list.size();
            for (int i = 0; i < N; i += L) {
                final int start = i;
                final int end = Math.min(N, i + L);
                result.add(new KoreanPrice(result.size(), list.subList(start, end), N == end));
            }
            return result;
        }

        public KoreanPriceList(List<Integer> koreanPriceList) {
            final List<Integer> list = new ArrayList(koreanPriceList);
            Collections.reverse(list);

            List<KoreanPrice> partitionKoreanPriceList = partition(list, 4);
            Collections.reverse(partitionKoreanPriceList);

            this.koreanPriceList = Collections.unmodifiableList(partitionKoreanPriceList);
        }

        @Override
        public KoreanPrice get(int index) {
            return koreanPriceList.get(index);
        }

        @Override
        public int size() {
            return koreanPriceList.size();
        }
    }

    interface PriceDisplay {
        static String defaultDisplay(KoreanPrice price){
            if (price.isLastPosition()){
                DecimalFormat df = new DecimalFormat("#,###");
                return df.format(price.priceByDigits());
            }
            return String.format("%d,%03d", price.digits4(), price.digits3());

        }

        default String display(KoreanPrice price){
            return PriceDisplay.defaultDisplay(price);
        }
    }

    static class KoreanPrice {
        @Getter
        private final int position;
        @Getter
        private final boolean lastPosition;
        private final List<Integer> koreanPriceSubList;
        private final static int[] digits = {1_000, 100, 10, 1};

        KoreanPrice(int position, List<Integer> koreanPriceSubList, boolean lastPosition) {
            this.position = position;

            final List<Integer> list = new ArrayList(koreanPriceSubList);
            Collections.reverse(list);
            this.koreanPriceSubList = Collections.unmodifiableList(list);

            this.lastPosition = lastPosition;

            log.debug("({} > {}), {}", position, lastPosition, list);
        }

        int priceByDigits(int pos){
            final int MAX_POS = digitsSize();

            if (0 > pos || MAX_POS < pos){
                return -1;
            }

            return digits(pos) * koreanPriceSubList.get(pos);
        }

        private int digits(int pos){
            final int MAX_POS = digitsSize();
            final int MAX_DIGITS_SIZE = digits.length;

            if (0 > pos || MAX_POS < pos){
                return -1;
            }
            int p = isLastPosAndUnderMaxPosSize() ? (MAX_DIGITS_SIZE - MAX_POS) + pos : pos;
            return digits[p];
        }

        private boolean isLastPosAndUnderMaxPosSize() {
            return lastPosition && 4 != digitsSize();
        }
        public int digitsSize() { return koreanPriceSubList.size(); }

        public int priceByDigits(){
            return IntStream.rangeClosed(0, zeroBaseSubListSize())
                     .map(this::priceByDigits)
                     .sum();
        }

        private int zeroBaseSubListSize() {
            return digitsSize() - 1;
        }

        /**
         * 4 번째 자릿수 숫자
         * @return 4번째 자릿수 숫자 혹은
         */
        public int digits4(){
            return koreanPriceSubList.get(0);
        }

        public int digits3(){
            return IntStream.rangeClosed(1, zeroBaseSubListSize())
                            .map(this::priceByDigits)
                            .sum();
        }
//
        @Override
        public String toString() {
            return String.format("(%2d) %d(%d,%d)"
                                 , position
                                 , priceByDigits(), digits4(), digits3()
            );
        }
    }


@Ignore
    @Test
    public void test(){
//        long price = 987_654_321L;
        long price = 10;

        String test = "" + price;

        List<Integer> list = Stream.of(test.split(""))
                                   .filter(s->s.matches("[0-9]"))
                                   .map(Integer::parseInt)
                                   .collect(toList());

        System.out.println(list);
        KoreanPriceList koreanPriceList = new KoreanPriceList(list);
        // System.out.println(koreanPriceList);

        koreanPriceList.stream().map(PriceDisplay::defaultDisplay).forEach(System.out::println);

        System.out.println(NumberFormat.getCurrencyInstance(Locale.KOREA).format(price));
    }
}
