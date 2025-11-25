package com.nasa.apodexplorer.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfiguration {

	@Bean
	public Caffeine<Object, Object> getCaffeine(){
		
		Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
		caffeine.expireAfterWrite(10, TimeUnit.MINUTES);
		caffeine.maximumSize(50);
		return caffeine;
	}
	
	@Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("apodExplorerCache");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}