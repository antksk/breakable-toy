package com.github.antksk.breakabletoy.ddd.blender;

import java.util.Arrays;
import java.util.List;

import lombok.Value;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Value
public class MixingResource {
    private String name;

    public static MixingResource of(String name){
        return new MixingResource(name);
    }

    public static List<MixingResource> withStringResource(String[] fruits) {
        return withStringResource(Arrays.asList(fruits));
    }

    public static List<MixingResource> withStringResource(List<String> fruits) {
        return fruits.stream().map(MixingResource::of).collect(toList());
    }

    public static String joiningByName(String delimiter, List<MixingResource> mixingResources){
        return mixingResources.stream().map(MixingResource::getName).collect(joining(delimiter));
    }
}
