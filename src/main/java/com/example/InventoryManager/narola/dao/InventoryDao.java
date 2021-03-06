package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, Integer> {

    @Query("SELECT inventory FROM Inventory inventory WHERE CONCAT(inventory.productName, inventory.productRating) LIKE %?1% ")
    List<Inventory> searchAny(String value);

    /*Inventory findByProductName(String productName);*/
}
