package com.admxj.spring.SpringDemo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisTemplate(){
        ValueOperations opsForValue = redisTemplate.opsForValue();

        opsForValue.set("admxj","haha123");

        //设置过期时间
        this.redisTemplate.expire("admxj",30, TimeUnit.SECONDS);
    }
}