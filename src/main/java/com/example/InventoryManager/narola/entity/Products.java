package com.example.InventoryManager.narola.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String description;
    /*private int catogId;*/

    @ManyToOne
    @JoinColumn(name = "catogId", nullable = false)
    //@JsonIgnore
    @JsonIgnoreProperties("product")
    private Categories category;

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

    public Categories getCategory() {
        return category;
    }
    public void setCategory(Categories category) {
        this.category = category;
    }
}
