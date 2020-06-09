package com.github.antksk.breakabletoy.java8;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

@Slf4j
public class CollectorTest {

    interface ToFormatString {
        String toFormatString();
    }

    private static class MyString {
        private StringBuilder sb;
        public MyString() {
            sb = new StringBuilder();
        }

        public void add(String s){
            sb.append(s);
        }

        public void combine(MyString t){
            sb.append(t.sb);
        }

        @Override
        public String toString() {
            return "MyString{" +
                "sb=" + sb +
                '}';
        }
    }

    <T extends ToFormatString> Collector<T, ?, MyString>  toFormatString(){

        return new Collector<T, MyString, MyString>() {
            @Override
            public Supplier<MyString> supplier() {
                log.debug("supplier");
                return MyString::new;
            }

            @Override
            public BiConsumer<MyString, T> accumulator() {
                log.debug("accumulator");
                return (r,t)->{
                    log.debug(" > BiConsumer<MyString, T> {}.add({})", r, t.toFormatString());
                    r.add(t.toFormatString());
                };
            }

            @Override
            public BinaryOperator<MyString> combiner() {
                log.debug("combiner");
                return (l,r)->{
                    log.debug(" > BinaryOperator<MyString> {}.combine({})", l, r);
                    l.combine(r);
                    return l;
                };
            }

            @Override
            public Function<MyString, MyString> finisher() {
                log.debug("finisher");
                return (t)->{
                    log.debug(" > Function<MyString, MyString> t=>{}", t);
                    return t;
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                log.debug("characteristics");
                return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
            }
        };
    }


    static class TestData implements ToFormatString{
        private final String data;

        public TestData(String data) {
            this.data = data;
        }

        @Override
        public String toFormatString() {
            return String.format("[%s]", data);
        }
        static TestData of(String data){
            return new TestData(data);
        }
    }

    @Test
    public void test(){
        /*
        MyString collect = Stream.of(of("A"), of("B"), of("C"),of("A"), of("B"), of("C"),of("A"), of("B"), of("C"))
                                 .collect(toFormatString());
        System.out.println(collect);
*/
        MyString collect2 = IntStream.rangeClosed(1,10)
                                     .parallel()
                                     // .parallel()
                     .mapToObj(i->new TestData(String.valueOf(i)))
                     .collect(toFormatString())
            ;
        System.out.println(collect2);

    }

}
