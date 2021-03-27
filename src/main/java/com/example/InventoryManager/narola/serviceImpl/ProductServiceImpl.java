package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("noCache")
public class ProductServiceImpl extends ProductServiceImplAbstractClass {

    @Transactional
    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        return super.getAll(request);
    }

    @Transactional
    @Override
    public void save(Products product) {
        super.save(product);
    }

    @Transactional
    @Override
    public Products update(Products product) {
        return super.update(product);
    }

    @Transactional
    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Transactional
    @Override
    public List<ProductsModel> getAllProduct() {
        System.out.println("No cache method");
        return super.getAllProduct();
    }

    @Transactional
    @Override
    public Products getById(int id) {
        return super.getById(id);
    }
}