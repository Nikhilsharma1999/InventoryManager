package com.example.InventoryManager.Caching;


import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCache<Integer,Products> implements Cache<Integer, Products> {

    Map<Integer,Products> caching = new ConcurrentHashMap<>();

    @Override
    public void addOrUpdate(Integer key, Products value) {
        caching.put(key, value);
    }

    @Override
    public void remove(Integer key) {
        caching.remove(key);
    }

    @Override
    public List<Products> getAll() {
        return new ArrayList<>(caching.values());
    }

    @Override
    public Optional<Products> getByKey(Integer key) {
        return Optional.ofNullable(caching.get(key));
    }
}