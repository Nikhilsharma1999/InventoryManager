package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.Model.ProductSearchRequest;
import com.example.InventoryManager.narola.Model.ProductSearchResponse;
import com.example.InventoryManager.narola.Model.ProductSearchResponseWrapper;
import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/_products")
public class ProductController {
    @Autowired
    @Qualifier("ProductServiceAlias")
    private ProductService productService;

    @CrossOrigin
    @PostMapping/*("/_products")*/
    public ResponseEntity<ProductSearchResponseWrapper> getProducts(@RequestBody(required = false) ProductSearchRequest request) /*throws ValidationException*/ {
        List<ProductSearchResponse> products = null;
        if(request==null){
            products = productService.getAll(new ProductSearchRequest());
        }else {
            products = productService.getAll(request);
        }
        return ResponseEntity.ok(new ProductSearchResponseWrapper(products));
    }

    @CrossOrigin
    @GetMapping
    public List<ProductsModel> getAllProducts(){
        return productService.getAllProduct();
    }

    @PostMapping("/add")
    public List<ProductsModel> addProducts(@RequestBody Products products){
        productService.save(products);
        return getAllProducts();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    private Products updateProduct(@RequestBody Products product, @PathVariable int id){
        product.setProductId(id);
        return productService.update(product);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private List<ProductsModel> deleteProduct(/*@RequestParam int id*/@PathVariable int id){
        productService.delete(id);
        return getAllProducts();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    private Products getProductById(@PathVariable int id){
        return productService.getById(id);
    }

}
