package com.example.InventoryManager.Caching;

import com.example.InventoryManager.narola.Model.ProductsModel;

import java.util.List;
import java.util.Optional;

public interface Cache<Integer, Products> {

    void addOrUpdate(Integer key, Products value);

    void remove(Integer key);

    List<Products> getAll();

    Optional<Products> getByKey(Integer key);

}
