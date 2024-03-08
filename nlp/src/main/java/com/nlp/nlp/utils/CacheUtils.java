package com.nlp.nlp.utils;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheUtils {


    final static String cacheName = "cache1";
    final static String SESSION_CACHE = "user_session";
    static org.ehcache.CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();

    static {
        cacheManager.init();
        cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(10)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(500, TimeUnit.SECONDS))));
        cacheManager.createCache(SESSION_CACHE, CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(1000)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(2, TimeUnit.HOURS))));
    }

    public static org.ehcache.Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName, Object.class, Object.class);
    }

    public static void put(Object key, Object value) {
        getCache(cacheName).put(key, value);
    }

    public static void putSession(Object key, Object value) {
        getCache(SESSION_CACHE).put(key, value);
    }

    public static Object get(String key) {
        org.ehcache.Cache cache = getCache(cacheName);
        if (cache.get(key) != null) {
            return cache.get(key);
        }
        return null;
    }

    public static Object getSession(String key) {
        org.ehcache.Cache cache = getCache(SESSION_CACHE);
        if (cache.get(key) != null) {
            return cache.get(key);
        }
        return null;
    }

    public static void removeSession(String key) {
        org.ehcache.Cache cache = getCache(SESSION_CACHE);
        if (cache.get(key) != null) {
            Object obj = cache.get(key);
            cache.remove(key);
        }
    }

    public static Object getAndDelete(String key) {
        org.ehcache.Cache cache = getCache(cacheName);
        Object obj = cache.get(key);
        cache.remove(key);
        return obj;
    }

    public static boolean containsKeyInCache(String key) {
        org.ehcache.Cache cache = getCache(SESSION_CACHE);
        if (cache.containsKey(key)) {
            return true;
        }
        return false;
    }
}
