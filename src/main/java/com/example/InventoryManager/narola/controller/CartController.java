package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.entity.Cart;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Set<ProductsModel> getCartList(Principal principal){
        return cartService.getAllProductsByCartId(principal);
    }

    @PostMapping("{pId}")
    public Set<ProductsModel> addToCart(@PathVariable int pId, Principal principal){
        cartService.save(principal,pId);
        return getCartList(principal);
    }

    @DeleteMapping("/{pId}")
    public Set<ProductsModel> deleteFromCart(@PathVariable int pId,Principal principal){
        cartService.delete(principal,pId);
        return getCartList(principal);
    }
}