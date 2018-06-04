package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.google.common.collect.ImmutableMap;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import java.util.Map;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EA implements Unitable<Integer>{
    private static final int MIN_EA = 0;
    private static final int MAX_EA = 10_000_000;
    public static final String INFINITY_VALUE = "infinity";
    public static final String UNIT_NAME = "EA";

    private static final EA INFINITY = new EA(Integer.MIN_VALUE);
    private static final EA ZERO = EA.of(0);
    private static final EA ONE = EA.of(1);
    private static final EA HUNDRED = EA.of(100);
    private static final EA THOUSAND = EA.of(1_000);

    private static final Map<Integer,EA> cache = new ImmutableMap.Builder<Integer,EA>()
            .put(0, ZERO)
            .put(1, ONE)
            .put(100, HUNDRED)
            .put(1_000, THOUSAND)
            .build();

    private final int ea;

    private EA(int ea){
        this.ea = ea;
    }

    @Override
    public boolean isSingleElement() {
        return true;
    }

    @Override
    public Integer getValue() {
        return ea;
    }

    @Override
    public String toDisplayValue() {
        final String format = "%s %s";
        if( 0 < ea ) {
            return String.format(format, Unitable.super.toDisplayValue(), UNIT_NAME);
        }
        return String.format(format, INFINITY_VALUE, UNIT_NAME);
    }

    @Override
    public int compareTo(MerchandiseValueFactor<Integer> o) {
        return compareTo(getValue() - o.getValue());
    }

    public static EA infinity(){
        return INFINITY;
    }

    public static EA zero(){
        return ZERO;
    }

    public static EA one(){
        return ONE;
    }

    public static EA hundred(){
        return HUNDRED;
    }

    public static EA thousand(){
        return THOUSAND;
    }

    public static EA of(int ea){
        if( MIN_EA > ea || MAX_EA < ea ){
            return INFINITY;
        }
        if( cache.containsKey(ea) ){
            return cache.get(ea);
        }
        return new EA(ea);
    }
}
