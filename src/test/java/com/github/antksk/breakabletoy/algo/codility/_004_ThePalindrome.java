package com.github.antksk.breakabletoy.algo.codility;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class _004_ThePalindrome {
    public int find(String s){
        for (int rightIndex = s.length(); ;rightIndex++) {
            boolean flag = true;
            for (int leftIndex = 0; leftIndex < s.length(); leftIndex++) {
                int lastIndex = rightIndex - leftIndex - 1;
                if (lastIndex < s.length()){
                    log.debug("{} {} => {}, {}", leftIndex, lastIndex,  s.charAt(leftIndex), s.charAt(lastIndex));
                    if(s.charAt(leftIndex) != s.charAt(lastIndex)){
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) return rightIndex;
        }
    }

    @Test
    public void name() {
        _004_ThePalindrome thePalindrome = new _004_ThePalindrome();
        log.debug("{}", thePalindrome.find("abab"));

        int[] a = {1,2,3,4,5,4,66666};
        int i = Arrays.stream(a).sum() / a.length;
        System.out.println(i);
        // 양수, 음수, 최대값 최소값, 매우 큰 데이터
//        log.debug("{}", thePalindrome.find("abacaba"));
    }
}
