package com.SpringBoot.JournalApp.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object cachedValue = redisTemplate.opsForValue().get(key);
            if (cachedValue == null) {
                return null;
            }

            // If the cached value is already of the expected type, return it directly
            if (entityClass.isInstance(cachedValue)) {
                return entityClass.cast(cachedValue);
            }

            // Otherwise, try to deserialize from JSON string
            return objectMapper.readValue(cachedValue.toString(), entityClass);
        } catch (Exception e) {
            log.error("Error retrieving value from Redis for key: {} - {}", key, e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }
}