package com.github.antksk.breakabletoy.ddd.blender;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.github.antksk.breakabletoy.ddd.blender.mixer.FruitMixer;
import com.github.antksk.breakabletoy.ddd.blender.mixer.NutProductsMixer;
import com.github.antksk.breakabletoy.ddd.blender.mixer.PutTogetherMixer;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "blender-test")
public class BlenderTest {
    // 믹싱 전략
    final MixingStrategy shuffleMixingStrategy = Collections::shuffle;
    final MixingStrategy firstSecondSwapMixingStrategy = (List<MixingResource> mixingResource) -> {
        Collections.swap(mixingResource, 0, 1);
    };

    @Test
    public void 각_믹서기_별_섞는_기능_테스트() {
        display("{무조건 섞기} 과일 섞는 믹서기 : \n",
                FruitMixer.of("사과", "수박", "포도").rpmSetup(MixingRPM.STEP1).rpmSetup(MixingRPM.STEP3).mix(shuffleMixingStrategy));
        display("{앞 두개만 섞기} 과일 섞는 믹서기 : \n", FruitMixer.of("사과", "수박", "포도").mix(firstSecondSwapMixingStrategy));
        display("{무조건 섞기} 견과류 섞는 믹서기 : \n", NutProductsMixer.of("땅콩", "아몬드", "피칸").rpmSetup(MixingRPM.STEP2).mix(shuffleMixingStrategy));
        display("{앞 두개만 섞기} 견과류 섞는 믹서기 : \n", NutProductsMixer.of("땅콩", "아몬드", "피칸").mix(firstSecondSwapMixingStrategy));
    }

    @Test
    public void 복합_믹서기_기능_테스트() {
        PutTogetherMixer mixer = PutTogetherMixer.of(FruitMixer.of("사과", "수박", "포도"), NutProductsMixer.of("땅콩", "아몬드", "피칸"));
        display("총 : ", mixer.totalMixingResultSize());
        display("복합 믹서기 : ", mixer.rpmSetup(MixingRPM.STEP2).mix(shuffleMixingStrategy));
    }

    private void display(String prefix, MixingResult result) {
        display(prefix, result.toResult());
    }

    private void display(String prefix, Object o) {
        log.debug("\n----------------------------\n{}{}\n", prefix, o);
    }
}
