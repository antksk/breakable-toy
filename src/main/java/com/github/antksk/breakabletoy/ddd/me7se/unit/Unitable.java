package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import java.util.Objects;
import java.util.Optional;

public abstract class Unitable<T extends Number> implements MerchandiseValueFactor<T> {

    private final T value;
    private final String unitName;
    protected Unitable(T value, String unitName){
        this.value = value;
        this.unitName = Objects.requireNonNull(unitName, "require unit name").toUpperCase();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s%s", MerchandiseValueFactor.super.toDisplayValue(), getUnitName());
    }

    public String toDisplayValueWithUnitName(){
        final Optional<Unitable> subUnitable = getSubUnitable();
        if( subUnitable.isPresent() ){
            return String.format("%%s(%s)", toString(), subUnitable.get());
        }
        return toString();
    }

    public String getUnitName(){
        return unitName;
    }

    @Override
    public Optional<Unitable> getUnitable() {
        return Optional.of(this);
    }

    public Optional<Unitable> getSubUnitable() {
        return Optional.empty();
    }


}
