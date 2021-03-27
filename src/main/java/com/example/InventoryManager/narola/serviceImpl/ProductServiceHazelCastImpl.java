/*
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hazelcast")
public class ProductServiceHazelCastImpl extends ProductServiceImplAbstractClass {

    @Transactional
    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        return super.getAll(request);
    }

    @Transactional
    @Override
    @CacheEvict(value = "hazelCast",allEntries = true)
    public void save(Products product) {
        super.save(product);
    }

    @Transactional
    @Override
    @Caching(evict = {@CacheEvict(value = "hazelCast", allEntries = true)},put = {@CachePut(value = "hazelCast", key = "#product.productId")})
    public Products update(Products product) {
       return super.update(product);
    }

    @Transactional
    @Override
    @CacheEvict(value = "hazelCast", key = "#id", allEntries = true)
    public void delete(int id) {
        super.delete(id);
    }

    @Transactional
    @Override
    @Cacheable(value = "hazelCast",cacheManager = "hazelCastCacheManager")
    public List<ProductsModel> getAllProduct() {
        System.out.println("HazelCast");
        return super.getAllProduct();
    }

    @Transactional
    @Override
    @Cacheable(value = "hazelCast", key = "#id")
    public Products getById(int id) {
        return super.getById(id);
    }
}*/
