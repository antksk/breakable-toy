package com.github.antksk.breakabletoy.util;

import java.util.function.Function;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpServletRequestAnalysisResourceTest {

    @Test
    public void underscoreToCamelKey_test(){
        final Function<String, String> f = HttpServletRequestAnalysis.HttpServletRequestAnalysisResource::underscoreToCamelKey;
        assertThat(f.apply("test_1234")).isEqualTo("test1234");
        assertThat(f.apply("1234_test")).isEqualTo("1234Test");
        assertThat(f.apply("abc_abc")).isEqualTo("abcAbc");
        assertThat(f.apply("_abc")).isEqualTo("abc");
        assertThat(f.apply("_abc_abc")).isEqualTo("abcAbc");
    }

    @Test
    public void camelToUnderscoreKey_test(){
        final Function<String, String> f = HttpServletRequestAnalysis.HttpServletRequestAnalysisResource::camelToUnderscoreKey;
        assertThat(f.apply("test1234")).isEqualTo("test1234");
        assertThat(f.apply("1234Test")).isEqualTo("1234_test");
        assertThat(f.apply("abcAbc")).isEqualTo("abc_abc");
        assertThat(f.apply("AbcAbc")).isEqualTo("abc_abc");
    }

    private HttpServletRequestAnalysis.HttpServletRequestAnalysisResource generateResourceTestKey(String key) {
        return HttpServletRequestAnalysis
            .HttpServletRequestAnalysisResource.ofDefaultValueIsEmpty(key, "1111");
    }
}
