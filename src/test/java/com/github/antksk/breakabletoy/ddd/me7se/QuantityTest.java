package com.github.antksk.breakabletoy.ddd.me7se;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class QuantityTest {
//
//    static final Quantity _0_ = zero();
//    static final Quantity _1_ = one();
//    static final Quantity _100_ = hundred();
//    static final Quantity _1_000_ = thousand();
//    static final Quantity infinity = infinity();
//
//    static final List<Quantity> quantitys = new ImmutableList.Builder<Quantity>()
//            .add(infinity)
//            .add(_1_)
//            .add(_1_000_)
//            .add(_100_)
//            .add(_0_)
//            .build();
//
//    @Test
//    public void QTY_연산자_메서드_테스트(){
//        assertThat(_0_.equals(_0_)).isTrue();
//
//        assertThat(_0_.lessThan(_1_)).isTrue();
//        assertThat(_100_.lessThan(_1_000_)).isTrue();
//        assertThat(_1_000_.lessThan(infinity)).isTrue();
//
//        assertThat(_1_.greaterThan(_0_)).isTrue();
//    }
//
//    @Test
//    public void QTY_기본_sort_방식_테스트(){
//        final List<Quantity> sorted = quantitys.stream().sorted().collect(toList());
//        assertThat(sorted).isSorted();
//    }
}
