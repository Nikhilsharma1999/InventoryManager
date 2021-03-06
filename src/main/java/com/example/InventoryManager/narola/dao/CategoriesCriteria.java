package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Categories;

import java.util.List;

public interface CategoriesCriteria {
    List<Categories> searchAnyCategory(String value);
}
