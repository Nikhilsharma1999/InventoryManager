package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductSearchResponseWrapper;
import com.example.InventoryManager.narola.entity.Inventory;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/_products")
    public ResponseEntity<ProductSearchResponseWrapper> getProducts(@RequestBody ProductSearchRequest request) throws ValidationException {
        List<ProductSearchResponse> products = productService.getAll(request);
        return ResponseEntity.ok(new ProductSearchResponseWrapper(products));
    }

    @GetMapping("/_products/list")
    public List<Products> getAllProducts(){
        return productService.getAllProduct();
    }

    @PostMapping("/_products/add")
    public List<Products> addProducts(@RequestBody Products products){
        productService.save(products);
        return getAllProducts();
    }

    @PutMapping("/_products/update")
    private List<Products> updateProduct(@RequestBody Products product, @RequestParam int id){
        product.setProductId(id);
        productService.update(product);
        return getAllProducts();
    }

    @DeleteMapping("/_products/delete")
    private List<Products> deleteProduct(@RequestParam int id){
        productService.delete(id);
        return getAllProducts();
    }

    @GetMapping("/_products/getById")
    private Products getProductById(@RequestParam(value = "id") int id){
        return productService.getById(id);
    }
}
