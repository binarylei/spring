package com.github.binarylei.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		CacheManager cacheManager = context.getBean(CacheManager.class);
		Cache cache = cacheManager.getCache("cache-1");
		cache.put("1", "11");

	}

	@Bean
	public CacheManager getCacheManaer() {
		ConcurrentMapCache cache1 = new ConcurrentMapCache("cache-1");

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(cache1));
		return cacheManager;
	}
}
