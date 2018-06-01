package com.github.antksk.breakabletoy.ddd.blender;

import com.github.antksk.breakabletoy.ddd.blender.mixer.FruitMixingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public interface MixingResult {
    List<MixingResource> getMixingResource();
    int getTotalRpmCount();

    default String toResult() {
        return String.format("[%s] (%s)\n%s"
                , getClass().getSimpleName()
                , MixingResource.joiningByName(", ", getMixingResource())
                , mixingResult(getTotalRpmCount(), getMixingResource())
        );
    }

    default String mixingResult(int totalRpmCount, List<MixingResource> mixingResources){

        return IntStream.rangeClosed(1,totalRpmCount)
                .mapToObj(i->String.format("[%d] ... {{{ mixing }}} .... ",i))
                .collect(joining("\n"));
    }


    default Optional<FruitMixingResult> getDetailMixingResultWithFruit(){
        return Optional.empty();
    }

}
