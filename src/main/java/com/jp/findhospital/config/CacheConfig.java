package com.jp.findhospital.config;

import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;


@EnableCaching
@Configuration
public class CacheConfig {
    // 처음엔 ConcurrentCacheManager 를 사용하려 했으나 TTL 설정이 불가하여 ehcache 사용.
    // (로컬 캐시 이기도 하고, redis를 사용할 만큼 다양한 처리를 하지 않을 것이기 떄문)

    @Bean
    public CacheManager cacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("hospitalListCache", createCacheConfiguration())
                .withCache("hospitalIdCache",createCacheConfiguration())
                .build(true);
    }

    private CacheConfiguration<String, String> createCacheConfiguration() {
        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class,
                        ResourcePoolsBuilder.heap(200)) // Heap size in entries
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60))) // Time-to-live in seconds
                        .build();
    }
}
