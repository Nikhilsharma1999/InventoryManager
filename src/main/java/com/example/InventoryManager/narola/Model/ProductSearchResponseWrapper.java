package com.example.InventoryManager.narola.Model;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchResponseWrapper {
    private List<ProductSearchResponse> products = new ArrayList<>();

    private ProductSearchResponseWrapper(){}

    public ProductSearchResponseWrapper(List<ProductSearchResponse> products) {
        this.products = products;
    }

    public List<ProductSearchResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSearchResponse> products) {
        this.products = products;
    }
}
