package com.f.redis;

import com.f.redis.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/3/5 18:14
 */
@SpringBootTest
public class StringRedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //JSON工具类ObjectMapper,或者可以用fastjson:JSON.toJSONString(), JSON.parseObject()
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testStringRedisTemplate() throws JsonProcessingException {
        User user1 = new User();
        user1.setId(1);
        user1.setName("tom");
        user1.setAge(18);
        // 手动序列化
        String user1Json = MAPPER.writeValueAsString(user1);
        stringRedisTemplate.opsForValue().set("redis:user:1", user1Json);
        // 手动反序列化
        User result = MAPPER.readValue(stringRedisTemplate.opsForValue().get("redis:user:1"), User.class);
        System.out.println(result);
    }

    @Test
    void testHash() {
        // HSET <-> put
        stringRedisTemplate.opsForHash().put("redis:user:1", "id", "1");
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "18");
        // HMSET <-> putAll
        stringRedisTemplate.opsForHash().putAll("redis:user:1", map);
        // HGET <-> get
        String name = stringRedisTemplate.opsForHash().get("redis:user:1", "name").toString();
        System.out.println(name);
        // HMGET <-> multiGet
        List list = new ArrayList<>();
        list.add("id");
        list.add("age");
        List<Object> values = stringRedisTemplate.opsForHash().multiGet("redis:user:1", list);
        values.forEach(value -> {
            System.out.println(value);
        });
    }
}