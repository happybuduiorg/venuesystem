package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.exception.VenueSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Component
public class RedisService {

    private final JedisPool jedisPool;

    @Autowired
    public RedisService(JedisPool jedisPool){
        this.jedisPool=jedisPool;
    }
    //字符串String

    //设定key-value
    String set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }

    Long setValidTime(String key, int seconds){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(key,seconds);
        } finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }

    //根据key 获取value
    String get(String key){
        Jedis jedis=null;
        String res=null;
        try{
            jedis=jedisPool.getResource();
            res=jedis.get(key);
        }catch (VenueSystemException e){
            e.printStackTrace();
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
        return res;
    }

    //先获取key的值 再重新给它赋上新的值
    String getSet(String key,String value){
        Jedis jedis=null;
        try{
            jedis =jedisPool.getResource();
            return jedis.getSet(key,value);
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }

    //将对应key的值上浮
    Long incrBy(String key, long increament) {
        Long i = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            i = jedis.incrBy(key, increament);
        } catch (VenueSystemException e) {
            e.printStackTrace();
        } finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
        return i;
    }

    //Hash表

    //设置key中的一个field的一个值
    Long hSet(String key, String field,String value){
        Jedis jedis=null;
        long i=0;
        try{
            jedis=jedisPool.getResource();
            i = jedis.hset(key,field,value);
        }catch (VenueSystemException e){
            e.printStackTrace();
        }
        finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
        return i;
    }

    //设置key中多个field的值
     String hmSet(String key, Map<String,String> hash){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.hmset(key,hash);
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }

    //根据key和field获取value值
     String hGet(String key,String field){
        Jedis jedis=null;
        String res=null;
        try{
            jedis=jedisPool.getResource();
            res=jedis.hget(key,field);
        }catch (VenueSystemException e){
            e.printStackTrace();
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
        return res;
    }

    //删除key中某个field
    Long hDel(String key,String field){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.hdel(key,field);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //获取Hash表中key的所有fields(字段）
    Set<String> hKeys(String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.hkeys(key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //对于给定的key判读field是否存在
    Boolean hExists(String key,String field){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.hexists(key,field);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //列表List

    //左边插入一个元素
    Long lPrush(String key,String value){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.lpush(key,value);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //弹出并获取左边的第一个元素
    public String lPop(String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.lpop(key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //通过索引给元素重新赋值
    String lSet(String key,int index,String value){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.lset(key,index,value);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //通过索引获取列表元素
    String lIndex(String key,long index){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.lindex(key,index);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    //获取列表长度
    long lLen(String key){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            return jedis.llen(key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}