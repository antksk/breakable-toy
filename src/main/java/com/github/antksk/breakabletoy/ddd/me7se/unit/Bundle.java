package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

public class Bundle implements Unitable<Integer> {

    private final int value;
    private final Unitable<? extends Number> element;
    private final String bundleName;

    private Bundle(int value, Unitable<? extends Number> element, String bundleName){
        this.value = value;
        this.element = element;
        this.bundleName = bundleName;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toDisplayValue() {
        return null;
    }

    @Override
    public int compareTo(MerchandiseValueFactor<Integer> o) {
        return 0;
    }

    @Override
    public boolean isBundle() {
        return element.isBundle();
    }

    public static Bundle of(int value, String bundleName) {
        return null;
    }
}
