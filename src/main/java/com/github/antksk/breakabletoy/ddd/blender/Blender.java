package com.github.antksk.breakabletoy.ddd.blender;

import java.util.List;

public interface Blender {
    List<MixingResource> getResource();

    @FunctionalInterface
    interface RPM {
        int repate();
    }

    Blender rpmSetup(RPM rpm);
    MixingResult mix(MixingStrategy mixingStrategy);
}
