package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.Caching.InMemoryCache;
import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.dao.ProductCriteriaImpl;
import com.example.InventoryManager.narola.dao.ProductsDao;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private InMemoryCache<Integer,Products> inMemoryCache;

    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) throws ValidationException {

        if(request==null) throw new ValidationException("Invalid Request");
        if(request.getCatogId() < 0) throw new ValidationException("Invalid Category selected");

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
        Products productt= productsDao.findByProductName(product.getProductName());
        inMemoryCache.addOrUpdate(/*product*/productt.getProductId(),product);
    }

    @Override
    public void update(Products product) {
        inMemoryCache.addOrUpdate(product.getProductId(),product);
        productsDao.save(product);
    }

    @Override
    public void delete(int id) {
        inMemoryCache.remove(id);
        productsDao.deleteById(id);
    }

    @Override
    public List<Products> getAllProduct() {
        List<Products> products= inMemoryCache.getAll();

        if(products.size()==0){
            return productsDao.findAll();
        }else {
            return products;
        }
        /*List<Products> product = new ArrayList<>();
        product.addAll(productsDao.findAll());
        return product;*/
        /*return products.size() == 0 ? productsDao.findAll() : products;*/
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

