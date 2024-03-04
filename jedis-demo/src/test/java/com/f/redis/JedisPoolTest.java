package com.f.redis;

import com.f.redis.util.JedisConnectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/3/4 21:54
 */
public class JedisPoolTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = JedisConnectFactory.getJedis();
        jedis.select(0);
    }

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "jack");
        map.put("age", "18");
        jedis.hmset("redis:user:1", map);
        Map<String, String> data = jedis.hgetAll("redis:user:1");
        System.out.println(data);
    }

    @AfterEach
    public void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
