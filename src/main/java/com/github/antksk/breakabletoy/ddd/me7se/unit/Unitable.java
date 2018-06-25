package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import java.util.Optional;

public abstract class Unitable<T extends Number> implements MerchandiseValueFactor<T> {

    private final T value;
    private final String unitName;
    protected Unitable(T value, String unitName){
        this.value = value;
        this.unitName = unitName;
    }

    @Override
    public T getValue() {
        return value;
    }

    public String toDisplayValueWithUnitName(){
        final String format = "%s%s";
        return String.format(format, MerchandiseValueFactor.super.toDisplayValue(), getUnitName());
    }

    public String getUnitName(){
        return unitName;
    }

    @Override
    public Optional<Unitable> getUnitable() {
        return Optional.of(this);
    }


}
