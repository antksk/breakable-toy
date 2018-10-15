package com.github.antksk.breakabletoy.ddd.me7se.unit;

import java.util.Optional;

public class Bundle {

    private final Optional<IntUnit> topBundleUnit;
    private final Optional<IntUnit> meduimBundleUnit;
    private final Unitable<? extends Number> minimumUnit;

    public Bundle(IntUnit topBundleUnit, IntUnit meduimBundleUnit, Unitable<? extends Number> minimumUnit){
        this.topBundleUnit = Optional.ofNullable(topBundleUnit);
        this.meduimBundleUnit = Optional.ofNullable(meduimBundleUnit);
        this.minimumUnit = minimumUnit;
    }

    /*
    public String toDisplayValueWithUnitName(){
        final Optional<Unitable> subUnitable = getSubUnitable();
        if( subUnitable.isPresent() ){
            return String.format("%%s(%s)", toString(), subUnitable.get());
        }
        return toString();
    }

    @Override
    public String toDisplayValue() {
        final String format = "%s%s";
        if( 0 < getValue()) {
            return toDisplayValueWithUnitName();
        }
        return String.format(format, 0, getUnitName());
    }

    */

}
