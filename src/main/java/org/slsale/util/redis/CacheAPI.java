package org.slsale.util.redis;

/**
 * Created by dll on 2017/10/10.
 * 缓存API
 */
public interface CacheAPI {
    /**
     * 添加数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key,Object value);

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    boolean exist(String key);

    /**
     * 通过key获取value
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 根据key 删除value
     * @param key
     * @return
     */
    boolean delete(String key);

}
