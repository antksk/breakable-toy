package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import java.util.Optional;

public interface Unitable<T extends Number> extends MerchandiseValueFactor<T> {


    default boolean isSingleElement(){
        return false;
    }


    default boolean isBundle() {
        return false;
    }

    @Override
    default Optional<Unitable> getUnitable() {
        return Optional.of(this);
    }

}
