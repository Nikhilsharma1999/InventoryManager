package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.dao.ProductsDao;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.exception.ValidationException;
import com.example.InventoryManager.narola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class ProductServiceImplAbstractClass implements ProductService {

    @Autowired
    private ProductsDao productsDao;

    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        if(request==null) throw new ValidationException("Invalid Request");
        if(request.getCatogId() < 0) throw new ValidationException("Invalid Category selected");

        if(request.getRecordPerPage()==0){
            request.setRecordPerPage(5);
        }
        Pageable paging = PageRequest.of(request.getPageNo()-1,request.getRecordPerPage());

        List<Products> productEntity = productsDao.FindProduct(
                request.getSearchStr(),
                request.getCatogId(),
                request.getSortOrder(),
                request.getSortBy(), paging);
        return entityToModel(productEntity);
    }

    @Override
    public void save(Products product) {
        productsDao.save(product);
    }

    @Override
    public Products update(Products product) {
        return productsDao.save(product);
    }

    @Override
    public void delete(int id) {
        productsDao.deleteById(id);
    }

    @Override
    public List<ProductsModel> getAllProduct() {
        return productsDao.findAllProjectsWithCategories();
    }

    @Override
    public Products getById(int id) {
        return productsDao.findById(id).get();
    }

    private List<ProductSearchResponse> entityToModel(List<Products> productEntity){
        return productEntity.stream().map(myProducts -> {
            ProductSearchResponse response = new ProductSearchResponse();
            response.setProductId(myProducts.getProductId());
            response.setProductName(myProducts.getProductName());
            response.setDescription(myProducts.getDescription());
            response.setCatogName(myProducts.getCategory().getCatogName());
            return response;
        }).collect(Collectors.toList());
    }
}
