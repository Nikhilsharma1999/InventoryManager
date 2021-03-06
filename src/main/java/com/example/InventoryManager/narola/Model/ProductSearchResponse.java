package com.example.InventoryManager.narola.Model;

public class ProductSearchResponse {
    private int productId;
    private String catogName;
    private String productName;
    private String description;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCatogName() {
        return catogName;
    }

    public void setCatogName(String catogName) {
        this.catogName = catogName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
