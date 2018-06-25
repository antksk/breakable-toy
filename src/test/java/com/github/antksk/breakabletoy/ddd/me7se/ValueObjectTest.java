package com.github.antksk.breakabletoy.ddd.me7se;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ValueObjectTest {

    @Getter
    @EqualsAndHashCode(exclude = {"name"})
    @AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
    static final class User implements Comparable<User>{
        private final String name;
        private final int age;

        @Override
        public int compareTo(User o) {
            if( age < o.age ) return -1;
            if( age > o.age ) return 1;
            return 0;
        }
    }

    static final class Users implements Iterable<User>{
        private final List<User> users;

        public static final Comparator<User> userAgeComparator = (o1, o2) ->{
            if( o1.age < o2.age ) return -1;
            if( o1.age > o2.age ) return 1;
            return 0;
        };

        public Users(User ... users){
            this.users = Arrays.asList(users);
        }

        public Users(List<User> users){
            this.users = users;
        }

        public Users ordered(){
            return new Users(users.stream().sorted(userAgeComparator).collect(toList()));
        }

        public Users reversOrdered(){
            return new Users(users.stream().sorted(userAgeComparator.reversed()).collect(toList()));
        }

        @Override
        public Iterator<User> iterator() {
            return users.iterator();
        }
    }


    @Test
    public void test(){
        Users users = new Users(
                User.of("test-0",5),
                User.of("test-1",7),
                User.of("test-2",2),
                User.of("test-3",10),
                User.of("test-4",20),
                User.of("test-5",26),
                User.of("test-6",16)
        );
//
//        users.forEach(e->log.debug("{} [{}]",e.getName(),e.getAge()));
//        log.debug("------");
//        users.ordered().forEach(e->log.debug("{} [{}]",e.getName(),e.getAge()));
//        log.debug("------");
//        users.reversOrdered().forEach(e->log.debug("{} [{}]",e.getName(),e.getAge()));
//
//
        users.forEach(ValueObjectTest::display);
        for(User e : users.ordered()){
            display(e);
        }
    }

    public static void display(User e){
        log.debug("{} [{}]",e.getName(),e.getAge());
    }

}
