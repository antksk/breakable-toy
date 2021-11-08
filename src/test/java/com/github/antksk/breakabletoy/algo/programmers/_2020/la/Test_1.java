package com.github.antksk.breakabletoy.algo.programmers._2020.la;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 문자열 s에 있는 알파벳 중 'a'와 'z'를 하나씩 선택하려고 합니다. 이때, 선택한 'a'와 'z' 사이의 문자열에 다른 'a'와 'z'가 없어야 합니다.
 * 예를 들어 주어진 문자열이 zabzczxa인 경우, 다음과 같이 세 가지 방법이 가능합니다.
 *
 * z a bzczxa
 * z a b z czxa
 * zabzc z x a
 * 그러나 z a bzc z xa 와 같은 선택은 선택된 'a' 와 'z' 사이의 문자열 bzc에 'z' 가 포함되어있으므로 불가능합니다.
 * 문자열 s가 매개변수로 주어질 때, 주어진 규칙에 맞게 'a' 와 'z'를 선택하는 서로 다른 방법의 가짓수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * 문자열 s의 길이는 1이상 100,000이하입니다.
 * 문자열은 알파벳 소문자로만 이루어져 있습니다.
 *
 * abcz	1
 * zabzczxa	3
 * abcd	0
 */
@Slf4j
public class Test_1 {

    private final static Pattern aToz = Pattern.compile("a[b-y]*z");
    private final static Pattern zToa = Pattern.compile("z[b-y]*a");
    public int solution(String s) {
        int answer = 0;
        Matcher m1 = aToz.matcher(s);
        Matcher m2 = zToa.matcher(s);

        while( m1.find() ) answer ++;
        while( m2.find() ) answer ++;
        return answer;
    }

    @Test
    public void test() {
        log.debug("{}", solution("abcz"));
        log.debug("{}", solution("zabzczxa"));
        log.debug("{}", solution("abcd"));
    }
}
