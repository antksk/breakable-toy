package com.github.antksk.breakabletoy.algo.codility;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeHelper;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class _001_BinaryGap {

    class Solution {
        public final Pattern p = Pattern.compile("1?(?<zeros>0+)1");
        public int solution(int N) {
            String binaryString = Integer.toBinaryString(N);
            Matcher m = p.matcher(binaryString);
            int result = 0;
            while(m.find()){
                final String zero = m.group("zeros");
                result = Math.max(result, zero.length());
            }
            return result;
        }
    }

    @Test
    public void test(){
        Solution s = new Solution();
        final int N = 1923845;
        log.debug("{} => {}", Integer.toBinaryString(N), s.solution(N));
    }

}
