package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.dao.*;
import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Inventory;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvenServiceImpl implements InventoryService {

    private InventoryDao inventoryDao;
    public InvenServiceImpl(@Autowired(required = false) InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ProductCriteriaImpl productCriteria;

    @Autowired
    private CategoriesCriteriaImpl categoriesCriteria;


    public List<Inventory> getAll() {
        List<Inventory> inventory = new ArrayList<>();
        inventory.addAll(inventoryDao.findAll());
        return inventory;
    }

    @Override
    public List<Inventory> getAllProductsWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        Page<Inventory> pagedResult = inventoryDao.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    public void save(Inventory inventory) {
        inventoryDao.save(inventory);
    }

    public void update(Inventory inventory) {
        inventoryDao.save(inventory);
    }

    public void delete(int id) {
        inventoryDao.deleteById(id);
    }

    public Inventory getById(int id) {
        return inventoryDao.findById(id).get();
    }

    public List<Inventory> searchProduct(String value){
        return inventoryDao.searchAny(value);
    }

    @Override
    public List<Categories> getProductsWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
        Page<Categories> pagedResult = null;
        if(sortBy==null){
            Pageable paging = PageRequest.of(pageNo-1,pageSize);
            pagedResult = categoriesDao.findAll(paging);
        }
        else{
            Pageable paging = PageRequest.of(pageNo-1,pageSize, Sort.by(sortBy).ascending());
            pagedResult = categoriesDao.findAll(paging);
        }
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Products> searchCriteria(String value) {
        return productCriteria.searchAnyProduct(value);
    }

    @Override
    public List<Categories> searchCategory(String value) {
        return categoriesCriteria.searchAnyCategory(value);
    }
}