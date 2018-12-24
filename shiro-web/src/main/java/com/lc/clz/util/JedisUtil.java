package com.lc.clz.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }

    /**
     * set SessionId
     * @param key
     * @param value
     */
    public void set(byte[] key,byte[] value){
        Jedis jedis=getResource();
        try {
            jedis.set(key, value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    /**
     * set SessionId timeout
     * @param key
     * @param timeout
     */
    public void expire(byte[] key, int timeout){
        Jedis jedis=getResource();
        try {
            jedis.expire(key, timeout);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    /**
     * 根据sessionId获取session
     * @param key
     * @return
     */
    public byte[] get(byte[] key){
        Jedis jedis=getResource();
        try {
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            jedis.close();
        }
    }

    /**
     * 删除sessionId
     * @param key
     */
    public void del(byte[] key){
        Jedis jedis=getResource();
        try {
            jedis.del(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    /**
     * 根据前缀获取所有shiro的session的key
     * @param shiro_session_prefix
     * @return
     */
    public Set<byte[]> keys(String shiro_session_prefix){
        Jedis jedis=getResource();
        try {
            return jedis.keys((shiro_session_prefix+"*").getBytes());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            jedis.close();
        }
    }
}
