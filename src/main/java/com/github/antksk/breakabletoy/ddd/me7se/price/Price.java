package com.github.antksk.breakabletoy.ddd.me7se.price;

/**
 * - ISO 4217 : https://ko.m.wikipedia.org/wiki/ISO_4217
 * - 대한민국 원 : https://ko.m.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD_%EC%9B%90
 */
public class Price implements Priceable {

    private static final String KRW = "KRW";

    // WON
    // 10
    // 50
    // 100
    // 500
    // 1_000
    // 5_000
    // 10_000
    // 50_000
    @Override
    public long getPrice() {
        return 0;
    }

    @Override
    public Type getType() {
        return Type.COST;
    }
}
