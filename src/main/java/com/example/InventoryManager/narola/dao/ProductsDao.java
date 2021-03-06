package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>,ProductCriteria {
    Products findByProductName(String value);
}
