package com.example.InventoryManager.narola.service;

import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Inventory;
import com.example.InventoryManager.narola.entity.Products;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAll();

    List<Inventory> getAllProductsWithPagination(Integer pageNo, Integer pageSize, String sortBy);

    void save(Inventory inventory);

    void update(Inventory inventory);

    void delete(int id);

    Inventory getById(int id);

    List<Inventory> searchProduct(String value);

    List<Categories> getProductsWithPagination(Integer pageNo, Integer pageSize, String sortBy);

    List<Products> searchCriteria(String value);

    List<Categories> searchCategory(String value);
}

