package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesDao extends JpaRepository<Categories, Integer> {
}
