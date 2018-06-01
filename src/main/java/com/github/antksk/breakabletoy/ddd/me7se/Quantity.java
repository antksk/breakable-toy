package com.github.antksk.breakabletoy.ddd.me7se;

import java.util.Optional;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Quantity implements MerchandiseValueFactor, Comparable<Quantity> {

    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 10_000_000;
    public static final String INFINITY_VALUE = "infinity";

    private static final Quantity INFINITY = new Quantity(Integer.MIN_VALUE);
    private static final Quantity ZERO = new Quantity(0);
    private static final Quantity ONE = new Quantity(1);
    private static final Quantity HUNDRED = new Quantity(100);
    private static final Quantity THOUSAND = new Quantity(1_000);

    private final int qty;

    private Quantity(int qty){
        this.qty = qty;
    }

    @Override
    public Optional<Quantity> getQuantity() {
        return Optional.of(this);
    }

    @Override
    public int getValue(){
        return qty;
    }

    @Override
    public int compareTo(Quantity quantity) {
        return qty - quantity.qty;
    }

    /**
     * this &lt; target quantity
     * @param quantity
     * @return
     */
    public boolean lessThen(Quantity quantity){
        return 0 > compareTo(quantity);
    }

    /**
     * this &gt; target quantity
     * @param quantity
     * @return
     */
    public boolean greaterThen(Quantity quantity){
        return 0 < compareTo(quantity);
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
    public static Quantity create(int qty){
        if( MIN_QUANTITY > qty || MAX_QUANTITY < qty ){
            return INFINITY;
        }
        return new Quantity(qty);
    }

    @Override
    public String toString() {
        return String.format("%s", equals(INFINITY) ? INFINITY_VALUE : getValue());
    }
}
