package com.how2j.Jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    // 创建redis客户端对象
    private static Jedis jedis = new Jedis("192.168.244.128");

    /**
     * String
     */
    @Test
    public void test() {
        jedis.set("k2", "hello Jedis");
        System.out.println(jedis.get("k1"));
    }

    /**
     * Hash
     */
    @Test
    public void test2() {
        jedis.hset("u1", "username", "qianke");
        jedis.hset("u1", "email", "123@126.com");
        System.out.println(jedis.hget("u1", "username"));
    }

    /**
     * List
     */
    @Test
    public void test3() {
        jedis.lpush("L1", "qianke", "lisi", "zhangsan");
        List<String> list = jedis.lrange("L1", 0, -1);
        System.out.println(list);
    }

    /**
     * Set
     */
    @Test
    public void test4() {
        jedis.sadd("s1", "j", "d", "k");
        Set<String> set = jedis.smembers("s1");
        System.out.println(set);
    }

    /**
     * Zset
     */
    @Test
    public void test5() {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("qianke", 88.0);
        map.put("lisi", 55.0);
        map.put("wanger", 77.0);
        jedis.zadd("z1", map);
    }
}
