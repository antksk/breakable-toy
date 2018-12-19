package com.github.antksk.breakabletoy.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;
@Slf4j
public class HttpServletRequestAnalysisTest {

    private HttpServletRequestAnalysis requestAnalysis;

    @Before
    public void setUp() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("a","test a");
        request.addParameter("b","1234");
        request.addParameter("c","1,234");
        requestAnalysis = HttpServletRequestAnalysis.of(request);
    }

    @Test
    public void xForwardedFor() {
    }

    @Test
    public void remoteAddr() {
    }

    @Test
    public void ip() {
    }

    @Test
    public void pathVariables() {

    }




    @Test
    public void parameters() {
        requestAnalysis.parameters().entrySet()
                .forEach(e->log.debug("{}",e));
    }

    @Test
    public void headers() {
    }
}