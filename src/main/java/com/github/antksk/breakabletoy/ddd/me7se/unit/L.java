package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import lombok.EqualsAndHashCode;

/**
 * 부피 단위 리터(1,000 cc)
 */
@EqualsAndHashCode
public class L implements Unitable<Double> {
    private static final String UNIT_NAME = "L";
    private final double liter;
    private L(double liter){
        this.liter = liter;
    }

    @Override
    public Double getValue() {
        return liter;
    }

    @Override
    public String toDisplayValue() {
        final String format = "%s %s";
        return String.format(format, Unitable.super.toDisplayValue(), UNIT_NAME);
    }

    @Override
    public int compareTo(MerchandiseValueFactor<Double> o) {
        return compareTo(getValue() - o.getValue());
    }
}
