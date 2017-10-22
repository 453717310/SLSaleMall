package org.slsale.util.redis.impl;

import org.slsale.util.redis.CacheAPI;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by dll on 2017/9/28.
 * redis的工具类
 */
public class RedisAPI implements CacheAPI{

    private RedisTemplate redisTemplate;//redis模板

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 添加数据
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 通过key获取value
     *
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key 删除value
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {
        try {
            redisTemplate.delete(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
