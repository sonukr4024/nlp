package com.nlp.nlp.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheUtils {

    private static CacheManager cacheManager;

    public CacheUtils(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public static void setValueInCache(String key, Object value) {
        Cache cache = cacheManager.getCache(key);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public static Object getValueFormCache(String key) {
        Cache cache = cacheManager.getCache(key);
        return cache != null ? cache.get(key).get() : null;
    }

    public static boolean containsKeyInCache(String key) {
        Cache cache = cacheManager.getCache(key);
        return cache != null && cache.get(key) != null;
    }
}
