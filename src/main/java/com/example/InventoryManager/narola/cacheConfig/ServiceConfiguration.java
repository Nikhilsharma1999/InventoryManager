package com.example.InventoryManager.narola.cacheConfig;

import com.example.InventoryManager.narola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    public ProductService ProductServiceAlias(@Value("${caching.provider.impl}") String qualifier,
                                              @Value("${caching.provider.enabled}")boolean isEnabled){
        if(!isEnabled){
            qualifier = "noCache";
        }
        return (ProductService) context.getBean(qualifier);
    }
}
