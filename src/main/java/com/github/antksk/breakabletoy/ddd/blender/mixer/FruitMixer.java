package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.github.antksk.breakabletoy.ddd.blender.MixingResource;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;
import com.github.antksk.breakabletoy.ddd.blender.MixingStrategy;

import java.util.List;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FruitMixer extends AbstractMixter {

    private FruitMixer(List<MixingResource> resources) {
        super(resources);
    }

    public static FruitMixer of(String ... fruits){
        return new FruitMixer(MixingResource.withStringResource(fruits));
    }

    public static FruitMixer of(List<MixingResource> fruits){
        return new FruitMixer(fruits);
    }


    @Override
    public MixingResult mix(MixingStrategy mixingStrategy) {
        return new FruitMixingResult(
                mixingStrategy.newMixingResource(getResource()),
                getTotalRpmCount()
        );
    }
}
