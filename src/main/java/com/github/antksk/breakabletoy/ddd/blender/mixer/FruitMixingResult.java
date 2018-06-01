package com.github.antksk.breakabletoy.ddd.blender.mixer;

import com.github.antksk.breakabletoy.ddd.blender.MixingResource;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;
import com.github.antksk.breakabletoy.util.RandomUtils;

import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class FruitMixingResult implements MixingResult {
    private List<MixingResource> mixingResources;
    private int totalRpmCount;
    private static final int MAX_RESULT_RATIO = 100;

    // 믹싱하고 남은 찌꺼기 비율
    @Getter(AccessLevel.PRIVATE)
    private int scrap;

    public FruitMixingResult(List<MixingResource> mixingResources, int totalRpmCount) {
        this.mixingResources = mixingResources;
        this.totalRpmCount = totalRpmCount;
        this.scrap = RandomUtils.intRange(10,30); // 10 ~ 30% 정도의 찌꺼기 생성
    }

    @Override
    public List<MixingResource> getMixingResource() {
        return mixingResources;
    }

    @Override
    public Optional<FruitMixingResult> getDetailMixingResultWithFruit() {
        return Optional.of(this);
    }

    // 실제 음료 생성 비율
    public int getHealthyDrinksRatio(){
        return MAX_RESULT_RATIO - getScrapResultRatio();
    }

    // 음료를 만들고 남은 찌거기 비율
    public int getScrapResultRatio(){
        return scrap;
    }



}
