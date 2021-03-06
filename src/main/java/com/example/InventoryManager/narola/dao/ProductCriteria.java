package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Products;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCriteria {
    List<Products> searchAnyProduct(String value);

    List<Products> FindProduct(String value, int catId, String sortOrder, String sortBy, Pageable page);
}
