package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class IntUnit extends Unitable<Integer>{

    private IntUnit(int value, String unitName){
        super(value, unitName.toUpperCase());
    }

    @Override
    public String toDisplayValue() {
        final String format = "%s%s";
        if( 0 < getValue()) {
            return toDisplayValueWithUnitName();
        }
        return String.format(format, 0, getUnitName());
    }

    @Override
    public int compareTo(MerchandiseValueFactor<Integer> o) {
        return compareToFactor(getValue() - o.getValue());
    }

    public static IntUnit of(int value, String unitName){
        return new IntUnit(value, unitName);
    }

}
