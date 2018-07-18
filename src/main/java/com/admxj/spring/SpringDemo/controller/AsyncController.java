package com.admxj.spring.SpringDemo.controller;

import com.admxj.spring.SpringDemo.entity.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @RequestMapping("/async")
    public Callable<Resource> async(){

        logger.info("主线程开始");

        Callable<Resource> result = new Callable<Resource>() {

            @Override
            public Resource call() throws Exception {

                logger.info("副线程开始");
                Thread.sleep(2000);
                logger.info("副线程结束");

                return resource;
            }
        };
        logger.info("主线程开结束");
        return result;
    }


}
