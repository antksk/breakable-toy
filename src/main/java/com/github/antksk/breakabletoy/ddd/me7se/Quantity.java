package com.github.antksk.breakabletoy.ddd.me7se;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

import lombok.EqualsAndHashCode;

/**
 * 수량(Quantity)
 *
 */
@EqualsAndHashCode
public final class Quantity implements MerchandiseValueFactor<Integer> {

    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 10_000_000;
    public static final String INFINITY_VALUE = "infinity";

    public static final Quantity INFINITY = new Quantity(Integer.MIN_VALUE);
    public static final int ZERO_VALUE = 0;
    public static final Quantity ZERO = Quantity.of(ZERO_VALUE);
    public static final int ONE_VALUE = 1;
    public static final Quantity ONE = Quantity.of(ONE_VALUE);
    public static final int HUNDRED_VALUE = 100;
    public static final Quantity HUNDRED = Quantity.of(HUNDRED_VALUE);
    public static final int THOUSAND_VALUE = 1_000;
    public static final Quantity THOUSAND = Quantity.of(THOUSAND_VALUE);

    private static final Map<Integer,Quantity> cache = new ImmutableMap.Builder<Integer,Quantity>()
            .put(ZERO_VALUE, ZERO)
            .put(ONE_VALUE, ONE)
            .put(HUNDRED_VALUE, HUNDRED)
            .put(THOUSAND_VALUE, THOUSAND)
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
