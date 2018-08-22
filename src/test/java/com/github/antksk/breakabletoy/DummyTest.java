package com.github.antksk.breakabletoy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DummyTest
{

    private static final List<String> testUris = Arrays.asList(
            "http://stash.wemakeprice.com/projects/WONDER/repos/upload-api/commits/2d8d7d947919a6cebce882c2d8019b4a2545dd71",
            "https://post.naver.com/viewer/postView.nhn?volumeNo=16281955&memberNo=31724756",
            "https://www.google.com/search?q=%EC%9D%B4%EB%AF%B8%EC%A7%80+%EB%A1%9C+%EA%B2%80%EC%83%89&source=lnms&tbm=isch&sa=X&ved=0ahUKEwinnofqt6rcAhXUUd4KHfUXBtAQ_AUICigB&biw=2519&bih=1303"
    );

    @Test
    public void test(){

        final String https = "((http[s]?|ftp):\\/)?\\/?";
        final String domain = "([^:\\/\\s]+)";
        final String pathVariableAndQueryString = "((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?";

        final String uriRegex = String.join("", https, domain, pathVariableAndQueryString);


        final String test = "^(?:http(s)?:\\/\\/)?[\\wㄱ-힣.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#\\[\\]ㄱ-힣@!\\$&%'\\(\\)\\*\\+,;=.]+$";


        testUris.forEach(e->{

            log.debug("{}",  Pattern.compile(test).matcher(e).matches());
        });



    }
}
