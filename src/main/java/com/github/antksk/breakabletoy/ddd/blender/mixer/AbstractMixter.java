package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.google.common.collect.Lists;

import com.github.antksk.breakabletoy.ddd.blender.Blender;
import com.github.antksk.breakabletoy.ddd.blender.MixingResource;

import java.util.List;

public abstract class AbstractMixter implements Blender {
    private final List<MixingResource> resources;

    private final List<RPM> rpms;

    protected AbstractMixter(List<MixingResource> resources) {
        this.resources = resources;
        this.rpms = Lists.newArrayList();
    }

    @Override
    public Blender rpmSetup(RPM rpm) {
        rpms.add(rpm);
        return this;
    }

    @Override
    public List<MixingResource> getResource() {
        return resources;
    }

    protected int getTotalRpmCount(){
        return rpms.stream().mapToInt(RPM::repate).sum();
    }

}
