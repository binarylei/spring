package com.github.binarylei.cache;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@Configurable
@ComponentScan(basePackages = "com.github.binarylei.cache")
@EnableCaching
public class CacheConfigure {

    @Bean
    public CacheManager getCacheManaer() {
        ConcurrentMapCache cache1 = new ConcurrentMapCache("accountCache");

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(cache1));
        return cacheManager;
    }
}
