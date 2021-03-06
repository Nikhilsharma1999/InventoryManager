package com.example.InventoryManager.narola.entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private int productId;
    @Column(name = "Product_Name")
    private String productName;
    @Column(name = "Product_rating")
    private int productRating;

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

    public int getProductRating() {
        return productRating;
    }
    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public Inventory() {
    }
}
