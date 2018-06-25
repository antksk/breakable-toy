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

}
