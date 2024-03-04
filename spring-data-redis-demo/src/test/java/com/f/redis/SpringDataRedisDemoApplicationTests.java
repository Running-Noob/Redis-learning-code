package com.f.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringDataRedisDemoApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "jack");
        // 读取String数据
        String name = redisTemplate.opsForValue().get("name").toString();
        System.out.println(name);
    }

}
