package com.github.antksk.breakabletoy.ddd.me7se;

import com.github.antksk.breakabletoy.ddd.me7se.unit.FloatUnit;
import com.github.antksk.breakabletoy.ddd.me7se.unit.IntUnit;
import com.github.antksk.breakabletoy.ddd.me7se.unit.Unitable;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.Set;

@Slf4j
public class AbstractUnitTest {
    /**
     * https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&query=%EB%AC%B4%EA%B2%8C%EB%B3%80%ED%99%98
     */
    @Test
    public void test() {
        Set<Unitable<? extends Number>> weight = Sets.newLinkedHashSet(
            IntUnit.of(1_000_000, "mg"),                    /** 밀리그램*/
            IntUnit.of(1_000, "g"),                         /** 그램 */
            IntUnit.of(1, "kg"),                            /** 킬로그램 */
            FloatUnit.of(0.001f, "t"),                      /** 톤 */
            //   DoubleUnit.of(1e-6, "kt"),                      /** 킬로 톤 */
            FloatUnit.of(15432.3584f, "gr"),                /** 그레인 */
            FloatUnit.of(35.273962f, "oz"),                 /** 온즈 */
            FloatUnit.of(2.204623f, "lb")                  /** 파운드 */
        );
        weight.forEach(e -> {
            log.debug("{}({})", e.getValue(), e.getUnitName());
        });
    }
}
