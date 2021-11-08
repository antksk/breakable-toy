package com.github.antksk.breakabletoy.spring;

import com.google.common.base.CaseFormat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/test")
public class GetMappingTestController {
    public String getSmartParam(HttpServletRequest request, String paramName, String defaultValue) {
        final String underscoreToCamelParam = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, paramName);
        return Optional.ofNullable(request.getParameter(paramName))
                       .orElse(Optional.ofNullable(request.getParameter(underscoreToCamelParam))
                                       .orElse(defaultValue));
    }

    @GetMapping("/param")
    public String test(HttpServletRequest request) {
        String result = "test(HttpServletRequest request)";
        final String paramName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_name");
        // CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "userName")
        System.out.println("param name : " + paramName + ", value : " + getSmartParam(request, "user_name", "no param"));
        return result;
    }

    @GetMapping(params = {"a"})
    public String test(@RequestParam(required = false) String a) {
        String result = "test(@RequestParam String a)";
        log.debug("result : {}, param : {}", result, a);
        return result;
    }

    @GetMapping(params = {"b"})
    public String test(@RequestParam(required = false) Integer b) {
        String result = "test(@RequestParam Integer b)";
        log.debug("result : {}, param : {}", result, b);
        return result;
    }
}
