package com.example.InventoryManager.narola.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catogId;
    private String catogName;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Products> product = new ArrayList<>();

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

    public List<Products> getProduct() {
        return product;
    }
    public void setProduct(List<Products> product) {
        this.product = product;
    }
}
