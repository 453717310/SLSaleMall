package org.slsale.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by dll on 2017/9/28.
 * redis的工具类
 */
public class JedisAPI {
    //连接池对象
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 把key 和 value 添加到jedis
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        //获取核心对象
        Jedis jedis=null;
        try {
            jedis= jedisPool.getResource();
            jedis.set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            returnResource(jedisPool,jedis);
        }
        return false;
    }

    /**
     * 判断key 是否存在
     * @param key
     * @return
     */
    public boolean exist(String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            returnResource(jedisPool,jedis);
        }
        return  false;
    }

    /**
     * 通过key 获取value
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis=null;
        String string=null;
        try {
            jedis = jedisPool.getResource();
            string = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            returnResource(jedisPool,jedis);
        }
        return string;
    }

    /**
     * 返还到连接池
     * @param jedisPool
     * @param jedis
     */
    public static void returnResource(JedisPool jedisPool,Jedis jedis){
        if (jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
}
