package com.github.antksk.breakabletoy;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class DummyTest {
    private static final List<String> testUris = Arrays.asList(
        "http://stash.wemakeprice.com/projects/WONDER/repos/upload-api/commits/2d8d7d947919a6cebce882c2d8019b4a2545dd71",
        "https://post.naver.com/viewer/postView.nhn?volumeNo=16281955&memberNo=31724756",
        "https://www.google.com/search?q=%EC%9D%B4%EB%AF%B8%EC%A7%80+%EB%A1%9C+%EA%B2%80%EC%83%89&source=lnms&tbm=isch&sa=X&ved" +
            "=0ahUKEwinnofqt6rcAhXUUd4KHfUXBtAQ_AUICigB&biw=2519&bih=1303"
    );

    @Test
    public void test() {
        final String https = "((http[s]?|ftp):\\/)?\\/?";
        final String domain = "([^:\\/\\s]+)";
        final String pathVariableAndQueryString = "((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?";
        final String uriRegex = String.join("", https, domain, pathVariableAndQueryString);
        final String test = "^(?:http(s)?:\\/\\/)?[\\wㄱ-힣.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#\\[\\]ㄱ-힣@!\\$&%'\\(\\)\\*\\+,;=.]+$";
        testUris.forEach(e -> {
            log.debug("{}", Pattern.compile(test).matcher(e).matches());
        });
    }

    @Getter
    @ToString
    static final class CompleteImmutableObject {
        private final int a;
        private final Integer b;
        private final String c;
        private final ImmutableCollection<Integer> d;

        private CompleteImmutableObject(int a, Integer b, String c, ImmutableCollection<Integer> d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Builder
        private CompleteImmutableObject(int a, Integer b, String c) {
            this(a, b, c, defaultInitD());
        }

        private static ImmutableCollection<Integer> defaultInitD() {
            return new ImmutableSet.Builder<Integer>()
                .add(1)
                .add(2)
                .add(3)
                .build();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test2() {
        CompleteImmutableObject completeImmutableObject = CompleteImmutableObject.builder()
                                                                                 .a(1)
                                                                                 .b(11234)
                                                                                 .c("c")
                                                                                 .build();
        log.debug("{}", completeImmutableObject);
        completeImmutableObject.getD().add(1);
    }
}
