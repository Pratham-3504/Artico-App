package com.Journal.project.service;

import com.Journal.project.api.response.WeatherRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
               return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(),entityClass);
        } catch (JsonProcessingException e) {
            log.error("Exception " + e);
            return null;
        }
    }

    public void set(String Key, Object o, Long ttl){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(Key,jsonValue,ttl, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("Exception " + e);
        }
    }
}

