package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.Caching.InMemoryCache;
import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.dao.ProductsDao;
import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("inMemory")
public class ProductServiceInMemoryCacheImpl extends ProductServiceImplAbstractClass {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private InMemoryCache<Integer,Products> inMemoryCache;

    @Transactional
    @Override
    public List<ProductSearchResponse> getAll(ProductSearchRequest request) {
        return super.getAll(request);
    }

    @Transactional
    @Override
    public void save(Products product) {
        super.save(product);
        Products inMemProduct= productsDao.findByProductName(product.getProductName());
        inMemoryCache.addOrUpdate(inMemProduct.getProductId(),product);
    }

    @Transactional
    @Override
    public Products update(Products product) {
        inMemoryCache.addOrUpdate(product.getProductId(),product);
        return super.update(product);
    }

    @Transactional
    @Override
    public void delete(int id) {
        inMemoryCache.remove(id);
        super.delete(id);
    }

    @Transactional
    @Override
    public List<ProductsModel> getAllProduct() {
        System.out.println("In-Memory Cache");
        List<Products> products= inMemoryCache.getAll();

        if(products.size()==0){
            List<ProductsModel> list=super.getAllProduct();
            list.forEach(product -> {
               Products p=new Products();
               Categories c=new Categories();
               c.setCatogId(product.getCatogId());
               c.setCatogName(product.getCatogName());
               p.setProductId(product.getProductId());
               p.setProductName(product.getProductName());
               p.setDescription(product.getDescription());
               p.setCategory(c);
               inMemoryCache.addOrUpdate(product.getProductId(),p);
            });
            return super.getAllProduct();
        }else {
            return entitiesToAllProducts(products);
        }
    }

    @Transactional
    @Override
    public Products getById(int id) {
        return super.getById(id);
    }

    private List<ProductsModel> entitiesToAllProducts(List<Products> products) {
        return products.stream().map(myProducts ->{
            ProductsModel model = new ProductsModel();
            model.setProductId(myProducts.getProductId());
            model.setProductName(myProducts.getProductName());
            model.setDescription(myProducts.getDescription());
            model.setCatogId(myProducts.getCategory().getCatogId());
            model.setCatogName(myProducts.getCategory().getCatogName());
            return model;
        }).collect(Collectors.toList());
    }
}

