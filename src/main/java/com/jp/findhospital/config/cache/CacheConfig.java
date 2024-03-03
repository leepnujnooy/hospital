package com.jp.findhospital.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.jp.findhospital.global.cache.CacheType;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    // 처음엔 ConcurrentCacheManager 를 사용하려 했으나 TTL 설정이 불가하여 caffeine 사용.
    // (로컬 캐시 이기도 하고, redis를 사용할 만큼 다양한 처리를 하지 않을 것이기 떄문)


    @Bean
    public List<CaffeineCache> caffeineCaches() {
        return Arrays.stream(CacheType.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cache.getExpiredAfterWhite(), TimeUnit.SECONDS)
                        .maximumSize(cache.getMaximumSize())
                        .build()))
                .toList();
    }
    @Bean
    public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }


//
//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        List<Ehcache> ehcaches = new ArrayList<>();
//
//        cacheManager.setCaches();
//        return cacheManager;
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheCacheManager() {
//        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml")); // ehcache.xml 위치 설정
//        cacheManagerFactoryBean.setShared(true);
//        return cacheManagerFactoryBean;
//    }


//    @Bean
//    public CacheManager ehCacheCacheManager() {
//        return CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("hospitalListCache", hospitalListCacheConfiguration())
//                .withCache("hospitalIdCache",hospitalHitsCacheConfiguration())
//                .build(true);
//    }

//
//    private CacheConfiguration<String, Page<Hospital>> hospitalListCacheConfiguration() {
//        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                        String.class, (Class<Page<Hospital>>) (Class<?>) Page.class,
//                        ResourcePoolsBuilder.heap(500)) // Heap size in entries
//                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(360))) // Time-to-live in seconds
//                .build();
//    }
//    private CacheConfiguration<String, Integer> hospitalHitsCacheConfiguration() {
//        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                        String.class, Integer.class,
//                        ResourcePoolsBuilder.heap(100)) // Heap size in entries
//                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60))) // Time-to-live in seconds
//                .build();
//    }
}
