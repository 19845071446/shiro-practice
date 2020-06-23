package com.example.shiropractice.WithSpringBoot.shiro.cache;

import com.example.shiropractice.WithSpringBoot.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

//自定义redis缓存的实现
public class RedisCache implements Cache {


    //授权或认证的name 进行区分
    private String name;

    public RedisCache(String s) {
        this.name = s;
    }

    @Override
    public Object get(Object o) throws CacheException {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.opsForHash().get(name, o.toString());
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForHash().put(name, o.toString(), o2);
        return null;
    }


    @Override
    public Object remove(Object o) throws CacheException {
        return getRedisTemplate().opsForHash().delete(name,o.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(name);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(name).intValue();
    }

    @Override
    public Set keys() {
        return getRedisTemplate().opsForHash().keys(name);
    }

    @Override
    public Collection values() {
        return getRedisTemplate().opsForHash().values(name);
    }


    public RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
