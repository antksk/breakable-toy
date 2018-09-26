package com.github.antksk.breakabletoy.algo.programmers._2018._11st;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/*
하루 동안 영화 관람객이 예매한 영화들이 무작위로 섞여서 주어집니다.
이때 가장 많이 예매된 순서대로 영화 제목을 정렬하려고 합니다.
관람객이 예매한 영화의 목록 movie가 매개변수로 주어질 때,
가장 많이 예매된 영화 순으로 영화 제목을 정렬하여 return 하도록 solution 함수를 완성해 주세요.
예매 수가 같은 영화는 사전 순으로 정렬하세요.
 */
@Slf4j
public class Test_1 {

    public static final class Movie{
        private final String name;
        private final long rank;

        public Movie(String name, long rank){
            this.name = name;
            this.rank = rank;
        }

        public static Movie create(Map.Entry<String, Long> e){
            return new Movie(e.getKey(),e.getValue());
        }


        public String getName(){
            return name;
        }

        public long getRank(){
            return rank;
        }



        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if( obj instanceof Movie){
                Movie target = (Movie)obj;
                return this.name.equals(target.name);
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("{name:%s, rank:%d}", name,rank);
        }
    }

    public String[] solution(String[] movie) {

        // Movie 객체로 변환
        Set<Movie> convertMovieSet = Arrays.stream(movie).collect(groupingBy(identity(), counting()))
                .entrySet().stream().map(Movie::create)
                .collect(toSet());

                log.debug("convertMovieSet : {}", convertMovieSet);

        // 정렬된 영화 제목 배열로 변환
        List<String> sortedList = convertMovieSet.stream()
                .sorted(Comparator.comparing(Movie::getRank).reversed().thenComparing(Movie::getName))
                .map(Movie::getName)
                .collect(toList());


        return sortedList.stream().toArray(String[]::new);

        /*
        final String[] resutl = new String[sortedList.size()];
        int i = 0;
        for (Iterator<String> iterator = sortedList.iterator(); iterator.hasNext(); i++) {

            resutl[i] = iterator.next();

            log.debug("zz : {},  {}", i, resutl[i] );
        }

        log.debug("zz : {}", resutl.length );

        return resutl;
*/
        // return new String[]{};
                /*
                return Arrays.stream(movie).collect(groupingBy(identity(), counting()))
                .entrySet().stream().map(Movie::create)
                .collect(toSet())
                .stream().sorted(Comparator.comparing(Movie::getName).thenComparing(Movie::getRank))
                .toArray(size -> new String[size])
        ;

*/
    }


    @Test
    public void test(){
        String[] result = solution(new String[]{"spy","ray","spy","room","once","ray","spy","once"});
        log.debug("result : {} / {}", result.length, result);
    }


}
