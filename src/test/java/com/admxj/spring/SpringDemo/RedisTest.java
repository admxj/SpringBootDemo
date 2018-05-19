package com.admxj.spring.SpringDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void set(){

        redisTemplate.opsForValue().set("haha","admxj");
    }

    @Test
    public void get(){

        Object haha = redisTemplate.opsForValue().get("haha");
        if (null == haha)
            logger.info("查出数据为空");
        else
            logger.info(haha.toString());


    }
}
