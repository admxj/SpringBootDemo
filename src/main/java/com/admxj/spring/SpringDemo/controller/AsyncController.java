package com.admxj.spring.SpringDemo.controller;

import com.admxj.spring.SpringDemo.entity.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

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

    @RequestMapping("/async2")
    public DeferredResult<Object> async2(){

        logger.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<Object> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        logger.info("主线程开结束");
        return result;
    }

}
