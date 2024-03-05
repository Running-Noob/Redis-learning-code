package com.f.redis;

import com.f.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringDataRedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "jack");
        // 读取String数据
        String name = redisTemplate.opsForValue().get("name").toString();
        System.out.println(name);
    }

    @Test
    public void testJson() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("tom");
        user1.setAge(18);
        redisTemplate.opsForValue().set("redis:user:1", user1);
        User result = (User) redisTemplate.opsForValue().get("redis:user:1");
        System.out.println(result);
    }
}
