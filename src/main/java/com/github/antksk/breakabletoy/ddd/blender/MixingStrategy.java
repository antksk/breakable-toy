package com.github.antksk.breakabletoy.ddd.blender;

import com.google.common.collect.Lists;

import java.util.List;

@FunctionalInterface
public interface MixingStrategy {
    void mixing(List<MixingResource> mixingResources);

    default List<MixingResource> newMixingResource(List<MixingResource> mixingResources){
        final List<MixingResource> newMixingResource = Lists.newArrayList(mixingResources);
        mixing(newMixingResource);
        return newMixingResource;
    }
}
