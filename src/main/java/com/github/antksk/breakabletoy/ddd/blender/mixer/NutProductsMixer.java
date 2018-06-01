package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.github.antksk.breakabletoy.ddd.blender.MixingResource;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;
import com.github.antksk.breakabletoy.ddd.blender.MixingStrategy;

import java.util.List;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class NutProductsMixer extends AbstractMixter {

    private NutProductsMixer(List<MixingResource> resources) {
        super(resources);
    }

    public static NutProductsMixer of(String ... nuts){
        return new NutProductsMixer(MixingResource.withStringResource(nuts));
    }

    @Override
    public MixingResult mix(MixingStrategy mixingStrategy) {
        return new NutProductsMixingResult(
                mixingStrategy.newMixingResource(getResource()),
                getTotalRpmCount()
        );
    }

}
