package com.grv.restproject.socialNetwork.controller;

import com.grv.restproject.socialNetwork.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldSampleController {

    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "HELLO WORLD";
    }

    @GetMapping(path = "/helloworldbean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HELLO WORLD");
    }



}
