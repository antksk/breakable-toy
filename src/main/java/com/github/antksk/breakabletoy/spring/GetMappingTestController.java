package com.github.antksk.breakabletoy.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GetMappingTestController {

//    @GetMapping("/test")
//    public String test(){
//        return "test()";
//    }

    @GetMapping(path = "/test")
    public String test(){
        String result = "test()";
        log.debug("result : {}, param : {}", result, "");
        return result;

    }

    @GetMapping(path = "/test", params = {"a"})
    public String test(@RequestParam(required = false) String a){
        String result = "test(@RequestParam String a)";
        log.debug("result : {}, param : {}", result, a);
        return result;

    }

    @GetMapping(path="/test",params = {"b"})
    public String test(@RequestParam(required = false) Integer b){
        String result = "test(@RequestParam Integer b)";
        log.debug("result : {}, param : {}", result, b);
        return result;
    }

}
