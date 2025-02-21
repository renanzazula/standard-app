package com.standard.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        try {
            CachingProvider provider = Caching.getCachingProvider();
            URL url = Objects.requireNonNull(getClass().getClassLoader().getResource("ehcache.xml"));
            System.out.println("Loading Ehcache XML from: " + url);
            URI uri = url.toURI();
            CacheManager cacheManager = provider.getCacheManager(uri, getClass().getClassLoader());
            return new JCacheCacheManager(cacheManager);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Ehcache configuration", e);
        }
    }

}
