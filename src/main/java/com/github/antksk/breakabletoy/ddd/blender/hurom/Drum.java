package com.github.antksk.breakabletoy.ddd.blender.hurom;

import com.github.antksk.breakabletoy.ddd.blender.MixingResource;
import com.github.antksk.breakabletoy.ddd.blender.mixer.FruitMixer;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(buildMethodName = "and")
public class Drum {
    @Getter(AccessLevel.PRIVATE)
    private HuromBBF20 body;

    @Singular
    private List<MixingResource> resources;

    public HuromBBF20 preparedBody(){
        // 믹서기 준비작업(Drum을 믹서기에 연결)
        return body
                .linkingDrum(this)
                .chooseMixingBar(FruitMixer.of(resources));
    }
}
