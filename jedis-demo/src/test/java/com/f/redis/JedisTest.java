package com.f.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/3/4 21:03
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach // 表示在当前类中的每个@Test方法之前执行该注解方法
    public void setUp() {
        // 1.建立连接
        jedis = new Jedis("192.168.44.130", 6379);
        // 2.设置密码
        jedis.auth("123456");
        // 3.选择库
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

    @AfterEach  // 表示在当前类中的每个@Test方法之后执行该注解方法
    public void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
