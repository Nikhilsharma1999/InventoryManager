package com.example.InventoryManager.narola.Model;

import lombok.*;

import java.io.Serializable;


public class ProductsModel implements Serializable {


    private static final long serialVersionUID = 6643428832125782320L;
    private int productId;
    private String productName;
    private String description;
    private int catogId;
    private String catogName;

    public ProductsModel(){}

    public ProductsModel(int productId, String productName, String description, int catogId, String catogName) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.catogId = catogId;
        this.catogName = catogName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getCatogId() {
        return catogId;
    }

    public void setCatogId(int catogId) {
        this.catogId = catogId;
    }

    public String getCatogName() {
        return catogName;
    }

    public void setCatogName(String catogName) {
        this.catogName = catogName;
    }
}