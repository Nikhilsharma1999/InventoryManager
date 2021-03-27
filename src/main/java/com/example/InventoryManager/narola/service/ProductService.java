package com.example.InventoryManager.narola.service;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;

import java.util.List;

public interface ProductService {
    List<ProductSearchResponse> getAll(ProductSearchRequest request);

    void save(Products product);

    Products update(Products product);

    void delete(int id);

    List<ProductsModel> getAllProduct();

    Products getById(int id);
}
