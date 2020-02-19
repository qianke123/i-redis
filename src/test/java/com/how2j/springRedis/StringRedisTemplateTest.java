package com.how2j.springRedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis-config.xml")
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * String
     */
    @Test
    public void test1() {
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        stringRedisTemplate.opsForValue().set("k3", "hello-springRedis");
        System.out.println(k1);
    }

    /**
     * hash
     */
    @Test
    public void test2() {
        stringRedisTemplate.opsForHash().put("h1", "phone", "1234");
    }

    /**
     * List
     */
    @Test
    public void test3() {
        List<String> list = stringRedisTemplate.opsForList().range("L1", 0, -1);
        System.out.println(list);
    }

    /**
     * Set
     */
    @Test
    public void test4() {
        stringRedisTemplate.opsForSet().add("s1", "spring", "redis");
        Set<String> members = stringRedisTemplate.opsForSet().members("s1");
        System.out.println(members);
    }

    /**
     * Zset
     */
    @Test
    public void test5() {
        stringRedisTemplate.opsForZSet().add("z1", "Ma", 80);
        Set<String> z1 = stringRedisTemplate.opsForZSet().range("z1", 0, -1);
        System.out.println(z1);
    }
}
