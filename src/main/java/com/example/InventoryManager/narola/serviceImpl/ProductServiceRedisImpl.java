package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("redis")
public class ProductServiceRedisImpl extends ProductServiceImplAbstractClass {

    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        return super.getAll(request);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "product", allEntries = true,cacheManager = "redisCacheManager")}
    ,put = {@CachePut(value = "product", key = "#product.productId",cacheManager = "redisCacheManager")})
    public void save(Products product) {
        super.save(product);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "product", allEntries = true,cacheManager = "redisCacheManager")}
   ,put = {@CachePut(value = "product", key = "#product.productId",cacheManager = "redisCacheManager")})
    public Products update(Products product) {
        return super.update(product);
    }

    @Override
    @CacheEvict(value = "product", key = "#id", allEntries = true,cacheManager = "redisCacheManager")
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    @Cacheable(value = "product",cacheManager = "redisCacheManager")
    public List<ProductsModel> getAllProduct() {
        System.out.println("Redis cache");
        return super.getAllProduct();
    }

    @Override
    @Cacheable(value = "product", key = "#id",cacheManager = "redisCacheManager")
    public Products getById(int id) {
        return super.getById(id);
    }
}
