package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("ehcache")
public class ProductServiceEhCacheImpl extends ProductServiceImplAbstractClass {


    @Transactional
    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        return super.getAll(request);
    }

    @Transactional
    @Override
    @CacheEvict(value = "productCache",allEntries = true,cacheManager = "ehCacheManager")
    public void save(Products product) {
        super.save(product);
    }

    @Transactional
    @Override
    @Caching(evict = {@CacheEvict(value = "productCache", allEntries = true,cacheManager = "ehCacheManager")},put = {@CachePut(value = "productCache", key = "#product.productId",cacheManager = "ehCacheManager")})
    public Products update(Products product) {
        return super.update(product);
    }

    @Transactional
    @Override
    @CacheEvict(value = "productCache", key = "#id", allEntries = true,cacheManager = "ehCacheManager")
    public void delete(int id) {
        super.delete(id);
    }

    @Transactional
    @Override
    @Cacheable(value = "productCache",cacheManager = "ehCacheManager")
    public List<ProductsModel> getAllProduct() {
        System.out.println("EhCache get method");
        return super.getAllProduct();
    }

    @Transactional
    @Override
    @Cacheable(value = "productCache", key = "#id",cacheManager = "ehCacheManager")
    public Products getById(int id) {
        return super.getById(id);
    }
}