package com.how2j.springRedis;

import com.how2j.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis-config.xml")
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * String
     */
    @Test
    public void test1() {
        redisTemplate.opsForValue().set("k1", "hello-spring-redis");
        String s = (String) redisTemplate.opsForValue().get("k1");
        System.out.println(s);
    }

    /**
     * Object
     */
    @Test
    public void test2() {
        User user = new User(123, "qianke123");
        redisTemplate.opsForValue().set("O", user);
        User user1 = (User)redisTemplate.opsForValue().get("O");
        System.out.println(user1);
    }

    /**
     * 事务执行
     */
    @Test
    public void test3() {
        try {
            // 开启事务
            redisTemplate.multi();
            redisTemplate.opsForValue().set("k1", "qianke");
            int i = 10 / 0;
            redisTemplate.opsForValue().set("k2", "qiankang");
            // 执行事务
            redisTemplate.exec();
        } catch(Exception e) {
            e.printStackTrace();
            // 取消事务的执行
            redisTemplate.discard();
        }
    }
}
