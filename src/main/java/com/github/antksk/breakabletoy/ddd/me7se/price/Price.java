package com.github.antksk.breakabletoy.ddd.me7se.price;

public class Price implements Priceable {
    @Override
    public long getPrice() {
        return 0;
    }

    @Override
    public Type getType() {
        return Type.COST;
    }
}
