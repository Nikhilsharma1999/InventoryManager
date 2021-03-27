package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>,ProductCriteria {
    Products findByProductName(String value);

    @Query("select new com.example.InventoryManager.narola.Model.ProductsModel(p.productId,p.productName,p.description, c.catogId,c.catogName) FROM Products p inner join p.category c")
    List<ProductsModel> findAllProjectsWithCategories();
}
