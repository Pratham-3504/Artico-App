package com.Journal.project.journalTest;

import com.Journal.project.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void redisConnection(){
        redisTemplate.opsForValue().set("salary","10k");
//        Object email = redisTemplate.opsForValue().get("email");
//        System.out.println(email);
    }
}
