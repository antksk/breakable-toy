package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.google.common.collect.Sets;

import com.github.antksk.breakabletoy.ddd.blender.Blender;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;
import com.github.antksk.breakabletoy.ddd.blender.MixingStrategy;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class PutTogetherMixer extends AbstractMixter {

    // TODO 여러 mixter타입을 보관하면서 관리가 필요 할지 아직 알수 없음
    // private final Set<Blender> mixers;


    private PutTogetherMixer(Set<Blender> mixers) {

        super( mixers.stream()
                .map(Blender::getResource)
                .flatMap(List::stream)
                .collect(toList()));

       //this.mixers = mixers;
    }

    public static PutTogetherMixer of(Blender ... blenders){
        return new PutTogetherMixer(Sets.newHashSet(blenders));
    }

    public int totalMixingResultSize() {
        return getResource().size();
    }

    @Override
    public MixingResult mix(MixingStrategy mixingStrategy) {
        return new PutTogetherMixingResult(
                mixingStrategy.newMixingResource(getResource()),
                getTotalRpmCount()
        );
    }
}
