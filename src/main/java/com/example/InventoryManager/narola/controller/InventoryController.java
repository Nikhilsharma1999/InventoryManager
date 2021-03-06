package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Inventory;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(@Autowired(required = false) InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/list")
    private List<Inventory> getAllProducts(){
        return inventoryService.getAll();
    }

    @GetMapping("/paginationWithSorting/{pageSize}/{pageNo}/{sortBy}")
    public ResponseEntity<List<Inventory>> getAllProductsWithPagination(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, @PathVariable("sortBy") String sortBy){
        List<Inventory> list = inventoryService.getAllProductsWithPagination(pageNo,pageSize,sortBy);
        return new ResponseEntity<List<Inventory>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("findById/{id}")
    private Inventory getProductById(@PathVariable("id") int id){
        return inventoryService.getById(id);
    }

    @GetMapping("/search/{value}")
    private List<Inventory> searchProduct(@PathVariable("value") String value){
        return inventoryService.searchProduct(value);
    }

    @PostMapping("/add")
    private List<Inventory> addProduct(@RequestBody Inventory inventory){
        inventoryService.save(inventory);
        return getAllProducts();
    }

    @PutMapping("/update/{id}")
    private List<Inventory> updateProduct(@RequestBody Inventory inventory, @PathVariable int id){
        inventory.setProductId(id);
        inventoryService.update(inventory);
        return getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    private List<Inventory> deleteProduct(@PathVariable("id") int id){
        inventoryService.delete(id);
        return getAllProducts();
    }

    @GetMapping("/pagination")
    public ResponseEntity getProductWithPagination(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy)
    {
        List<Categories> list = inventoryService.getProductsWithPagination(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/searchCriteria")
    private List<Products> searchCriteria(@RequestParam(required = true) String value){
        return inventoryService.searchCriteria(value);
    }

    @GetMapping("/searchCategory")
    private List<Categories> searchCategory(@RequestParam(required = true) String value){
        return inventoryService.searchCategory(value);
    }
}
