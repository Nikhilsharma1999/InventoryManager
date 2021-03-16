package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.dao.CategoriesDao;
import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public List<Categories> getAllCategories() {
        return categoriesDao.findAll();
    }
}
