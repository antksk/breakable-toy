package com.github.antksk.breakabletoy.ddd.me7se;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Quantity implements MerchandiseValueFactor<Integer> {

    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 10_000_000;
    public static final String INFINITY_VALUE = "infinity";

    private static final Quantity INFINITY = new Quantity(Integer.MIN_VALUE);
    private static final Quantity ZERO = Quantity.of(0);
    private static final Quantity ONE = Quantity.of(1);
    private static final Quantity HUNDRED = Quantity.of(100);
    private static final Quantity THOUSAND = Quantity.of(1_000);

    private static final Map<Integer,Quantity> cache = new ImmutableMap.Builder<Integer,Quantity>()
            .put(0, ZERO)
            .put(1, ONE)
            .put(100, HUNDRED)
            .put(1_000, THOUSAND)
            .build();

    private final int qty;

    private Quantity(int qty){
        this.qty = qty;
    }

    @Override
    public Optional<Quantity> getQuantity() {
        return Optional.of(this);
    }

    @Override
    public Integer getValue(){
        return qty;
    }

    @Override
    public int compareTo(MerchandiseValueFactor<Integer> o) {
        return compareToFactor(getValue() - o.getValue());
    }

    public static Quantity infinity(){
        return INFINITY;
    }

    public static Quantity zero(){
        return ZERO;
    }

    public static Quantity one(){
        return ONE;
    }

    public static Quantity hundred(){
        return HUNDRED;
    }

    public static Quantity thousand(){
        return THOUSAND;
    }

    /**
     * 수량의 범위는 MIN_QUANTITY ~ MAX_QUANTITY 까지 설정 가능
     * @param qty
     * @return
     */
    public static Quantity of(int qty){
        if( MIN_QUANTITY > qty || MAX_QUANTITY < qty ){
            return INFINITY;
        }
        if( cache.containsKey(qty) ){
            return cache.get(qty);
        }
        return new Quantity(qty);
    }

    @Override
    public String toString() {
        return String.format("%s", equals(INFINITY) ? INFINITY_VALUE : getValue());
    }


}
