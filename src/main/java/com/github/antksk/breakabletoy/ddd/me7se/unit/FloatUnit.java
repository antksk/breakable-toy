package com.github.antksk.breakabletoy.ddd.me7se.unit;

import com.github.antksk.breakabletoy.ddd.me7se.MerchandiseValueFactor;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class FloatUnit extends Unitable<Float>{

    private FloatUnit(float value, String unitName){
        super(value, unitName.toUpperCase());
    }


    @Override
    public int compareTo(MerchandiseValueFactor<Float> o) {
        return compareToFactor(getValue() - o.getValue());
    }

    public static FloatUnit of(float value, String unitName){
        return new FloatUnit(value, unitName);
    }

}
