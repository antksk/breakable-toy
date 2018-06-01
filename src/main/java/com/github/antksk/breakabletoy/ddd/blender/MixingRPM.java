package com.github.antksk.breakabletoy.ddd.blender;

import lombok.AllArgsConstructor;

/**
 * Mixing 횟수(회전수)를 결정함
 */
@AllArgsConstructor
public enum MixingRPM implements Blender.RPM{
    STEP1(step(1))
    , STEP2(step(2))
    , STEP3(step(3))
    ;

    Blender.RPM rpm;

    public static Blender.RPM step(int count){
        return () -> count;
    }

    @Override
    public int repate() {
        return rpm.repate();
    }
}
