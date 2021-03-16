package com.example.InventoryManager.narola.service;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Products;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService {
    List<ProductSearchResponse> getAll(ProductSearchRequest request) /*throws ValidationException*/;

    void save(Products product);

    void update(Products product);

    void delete(int id);

    List<Products> getAllProduct();

    Products getById(int id);

    List<ProductSearchResponse> getAllProducts();

    List<Categories> getAllCategories();
}
