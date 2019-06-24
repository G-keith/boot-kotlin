package com.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keith
 * @version 1.0
 * @date 2019-06-10
 */
@RestController
@RequestMapping("/java")
public class HelloWorldJava {

    @GetMapping("hello")
    public String test(){
        return "Hello JAVA";
    }
}
