package com.github.antksk.breakabletoy.ddd.blender.hurom;

import com.github.antksk.breakabletoy.ddd.blender.Blender;
import com.github.antksk.breakabletoy.ddd.blender.MixingRPM;
import com.github.antksk.breakabletoy.ddd.blender.MixingResult;

import java.util.Collections;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HuromBBF20 {
    private static final int OVER_RAP_SCREW_LIMIT = 3;
    private Blender mixingBar;

    private Drum drum;

    public HuromBBF20 linkingDrum(Drum drum){
        this.drum = drum;
        return this;
    }

    public HuromBBF20 chooseMixingBar(Blender mixingBar){
        this.mixingBar = mixingBar;
        return this;
    }

    public static Drum.DrumBuilder collectingWithDrum(){
        return Drum.builder().body(new HuromBBF20());
    }

    public boolean isOverRapScrew(){
        return OVER_RAP_SCREW_LIMIT < mixingBar.getResource().size();
    }

    public MixingResult smartTouch() {
        return isOverRapScrew()
                ? mixingBar
                    .rpmSetup(MixingRPM.STEP2)
                    .rpmSetup(MixingRPM.STEP3)
                    .rpmSetup(MixingRPM.STEP3)
                .mix(Collections::shuffle)
                : mixingBar
                    .rpmSetup(MixingRPM.STEP1)
                .mix(Collections::shuffle)
            ;
    }

}
