package com.github.antksk.breakabletoy.ddd.blender;

import com.github.antksk.breakabletoy.ddd.blender.hurom.HuromBBF20;
import com.github.antksk.breakabletoy.ddd.blender.mixer.FruitMixingResult;

import org.junit.Test;

public class HuromProductTest {
    @Test
    public void 스마트_터치_테스트(){
        HuromBBF20 bbf20 = HuromBBF20.collectingWithDrum()
                .resource(MixingResource.of("사과"))
                .resource(MixingResource.of("수박"))
                .resource(MixingResource.of("당근"))
                .resource(MixingResource.of("시금치"))
                .resource(MixingResource.of("바나나"))
                .and()
                .preparedBody();

        // mixing 에 대한 기본 동작 결과 확인
        MixingResult mixingResult = bbf20.smartTouch();
        System.out.println(mixingResult.toResult());

        // Hurom 기계가 과일용 블라인더일 경우 처리 결과
        mixingResult.getDetailMixingResultWithFruit().ifPresent(fruitMixingResult -> {
            System.out.printf(" - 음료수 생성율 : %d%%\n", fruitMixingResult.getHealthyDrinksRatio());
            System.out.printf(" - 음료수 찌꺼기 생성율 : %d%%\n", fruitMixingResult.getScrapResultRatio());
        });
    }
}
