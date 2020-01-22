package com.github.antksk.breakabletoy.code.test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodOverloadTest {
    static class MyList extends AbstractList<String> {
        private List<String> list;

        public MyList(Collection<String> collection) {
            list = new ArrayList<>(collection);
        }

        public Optional<String> find(String name) {
            final int index = list.indexOf(name);
            if (0 > index) {
                return Optional.empty();
            }
            return Optional.of(list.get(index));
        }

        @Override
        public String get(int index) {
            return list.get(index);
        }

        @Override
        public int size() {
            return list.size();
        }
    }

    static class MyClass {
        void method(Collection<String> collection) {
            log.debug("method(Collection<String> collection)");
            collection.forEach(e -> log.debug(" - {}", e));
        }

        void method(List<String> list) {
            log.debug("method(List<String> list)");
            list.forEach(e -> log.debug(" - {}", e));
        }

        void method(Set<String> set) {
            log.debug("method(Set<String> set)");
            set.forEach(e -> log.debug(" - {}", e));
        }
    }

    static MyList myList = new MyList(Lists.newArrayList("z", "a", "b", "c"));

    Collection<String> getCollection() {
        return myList;
    }

    List<String> getList() {
        return myList;
    }

    @Test
    public void test() {
        MyClass myClass = new MyClass();
        myClass.method(getCollection());
        myClass.method(getList());
        myClass.method(Sets.newSet("z", "a", "1"));
        myList.find("z").ifPresent(e -> log.debug("element : {}", e));


//        getCollection().stream().collect(Collectors.toCollection(Collectors.toCollection(()->new TreeSet<>());
    }

}
