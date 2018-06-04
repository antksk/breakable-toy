package com.github.antksk.breakabletoy.ddd.me7se;

import com.github.antksk.breakabletoy.ddd.me7se.unit.Bundle;
import com.github.antksk.breakabletoy.ddd.me7se.unit.EA;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitTest {
    @Test
    public void test(){
        log.debug("{}", EA.of(100).toDisplayValue());
    }
}
