package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @GetMapping
    private List<Categories> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
