package com.example.InventoryManager.narola.service;

import com.example.InventoryManager.narola.Model.ProductsModel;

import java.security.Principal;
import java.util.Set;

public interface CartService {

    void save(Principal principal, int pId);
    /*Cart update(Cart cart);*/
    void delete(Principal principal,int pId);
    Set<ProductsModel> getAllProductsByCartId(Principal principal);
}