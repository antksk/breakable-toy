package com.github.antksk.breakabletoy.code.test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.github.antksk.breakabletoy.code.test.Java8StreamTest.Dish.fish;
import static com.github.antksk.breakabletoy.code.test.Java8StreamTest.Dish.meat;
import static com.github.antksk.breakabletoy.code.test.Java8StreamTest.Dish.other;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Slf4j
public class Java8StreamTest {

    private static final List<Dish> menu = Collections.unmodifiableList(asList(
          meat("pork", 800)
        , meat("beef", 700)
        , meat("chicken", 400)
        , other("french fries", true, 530)
        , other("rice", true, 530)
        , other("season fruit", true, 120)
        , other("pizza", true, 550)
        , fish("prawns", 300)
        , fish("salmon", 450)

    ));

    static Predicate<Dish> lessThanCalories(final int calories){
        return (d) -> d.getCalories() < calories;
    }

    static List<Dish> menus( Dish ... menus ){
        return Collections.unmodifiableList(asList(menus));
    }



    @Test
    public void limitTest(){

        Set<String> result =
            /** [STEP 1] 원재료 스트림 생성 */
            testMenus().stream()
                       /** [STEP 2] 1차 공정, 칼로리 조건에 맞는 지 확인(ReferencePipeline.filter 호출 > StatelessOp ) */
                       .filter((e)->{
                 log.debug("filter : {}", e);
                 return lessThanCalories(500).test(e);
             })
                       /** [STEP 3] 2차 공정, 칼로리 기준으로 오름차순 정렬(ReferencePinpeline.sorted 호출 > StatefulOp > RefSortingSink > list.sort ) */
//             .sorted((e, e2)->{
//                 log.debug("sorted : {}, {}", e, e2);
//                 return comparing(Dish::getCalories).compare(e, e2);
//             })
                       /** [STEP 4] 3차 공정, 요리(Dish)이름만 취합(StatelessOp) */
                       .map((e)->{
                 log.debug("map : {}", e );
                 return e.getName();
             })
                       .limit(3)
                       .collect(toSet());
    }

    private List<Dish> testMenus() {
        return menus(
            meat("돼지고기", 800)
            , meat("소고기", 700)
            , meat("치킨", 400)
            , other("감자 튀김", true, 530)
            , other("밥", true, 330)
            , other("계절 과일", true, 120)
            , other("피자", true, 550)
            , fish("새우", 300)
            , fish("연어", 450)
        );
    }

    @Test
    public void lowCaloricDishsName(){


        Stream<Dish> createStream = menus(
            meat("돼지고기", 800)
            , meat("소고기", 700)
            , meat("치킨", 400)
            , other("감자 튀김", true, 530)
            , other("밥", true, 530)
            , other("계절 과일", true, 120)
            , other("피자", true, 550)
            , fish("새우", 300)
            , fish("연어", 450)
        ).stream();

        /** [STEP 2] 1차 공정, 칼로리 조건에 맞는 지 확인(ReferencePipeline.filter 호출 > StatelessOp ) */
        Stream<Dish> filterStream = createStream.filter(lessThanCalories(400));

        /** [STEP 3] 2차 공정, 칼로리 기준으로 오름차순 정렬(ReferencePinpeline.sorted 호출 > StatefulOp > RefSortingSink > list.sort ) */
        Stream<Dish> sortedStream = filterStream.sorted(comparing(Dish::getCalories));


        /** [STEP 4] 3차 공정, 요리(Dish)이름만 취합(StatelessOp) */
        Stream<String> mapStream = sortedStream.map(Dish::getName);


        log.debug( "\n" +
                       "- {}\n" +
                       "- {}\n" +
                       "- {}\n" +
                       "- {}\n" +
                       "\n\n [result] : {}", createStream, filterStream, sortedStream, mapStream, mapStream.collect(toSet()));



    }

    @ToString(onlyExplicitlyIncluded = true)
    @Getter
    static final class Dish {
        enum Type {
            MEAT, FISh, OTHER
        }

        @ToString.Include
        private final String name;
        private final boolean vegetarian;
        @ToString.Include
        private final int calories;
        private final Type type;

        @Builder
        private Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public static Dish meat(String name, int calories){
            return Dish.builder()
                       .name(name)
                       .vegetarian(false)
                       .calories(calories)
                       .type(Type.MEAT)
                .build();
        }

        public static Dish fish(String name, int calories){
            return Dish.builder()
                       .name(name)
                       .vegetarian(false)
                       .calories(calories)
                       .type(Type.FISh)
                       .build();
        }

        public static Dish other(String name, boolean vegetarian, int calories){
            return Dish.builder()
                       .name(name)
                       .vegetarian(vegetarian)
                       .calories(calories)
                       .type(Type.OTHER)
                       .build();
        }
    }

    @Test
    public void test(){
        log.debug("{}", Stream.generate(() -> "gen").limit(5).collect(toList()));
    }
}
