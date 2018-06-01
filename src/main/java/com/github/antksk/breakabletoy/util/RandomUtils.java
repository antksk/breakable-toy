package com.github.antksk.breakabletoy.util;

import java.util.Random;

public class RandomUtils {
    public static int intRange(int startInclusive, int endInclusive){
        return new Random().nextInt((endInclusive - startInclusive) + 1) + startInclusive;
    }
}
