package com.example.InventoryManager.narola.cacheConfig;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class CacheResolverConfiguration extends CachingConfigurerSupport {

    @Bean
    @Primary
    public CacheManager ehCacheManager(){
        CacheManager cacheManager;
        net.sf.ehcache.CacheManager ehCacheManager;

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("productCache");
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        cacheConfiguration.setMaxEntriesLocalHeap(1000);
        net.sf.ehcache.config.Configuration config
                = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);
        ehCacheManager = net.sf.ehcache.CacheManager.newInstance(config);
        cacheManager = new EhCacheCacheManager(ehCacheManager);
        return cacheManager;
    }

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()).build();
    }

    /*@Bean
    public CacheManager hazelCastCacheManager(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }
    
    @Bean
    public Config hazelcastConfig(){
        return new Config().setInstanceName("hazelcastInstance")
                .addMapConfig(new MapConfig().setName("hazelCast")
                *//*.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU)*//*
                .setTimeToLiveSeconds(3000));
    }*/
}