package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.github.antksk.breakabletoy.ddd.blender.MixingResource;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;

import java.util.List;
import java.util.Optional;

import lombok.Value;

@Value
class PutTogetherMixingResult implements MixingResult {

    private List<MixingResource> mixingResources;
    private int totalRpmCount;

    @Override
    public List<MixingResource> getMixingResource() {
        return mixingResources;
    }

}
