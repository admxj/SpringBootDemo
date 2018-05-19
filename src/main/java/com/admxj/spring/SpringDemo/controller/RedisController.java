package com.admxj.spring.SpringDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/index")
    public String index(){

        return null;
    }

}
