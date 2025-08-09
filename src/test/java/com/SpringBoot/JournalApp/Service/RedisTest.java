package com.SpringBoot.JournalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSendMail() {
        // First, let's see what keys exist
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("All keys in Redis: " + keys);

        // Try to get the salary
        Object salary = redisTemplate.opsForValue().get("salary");
        System.out.println("Salary value: " + salary);
    }
}