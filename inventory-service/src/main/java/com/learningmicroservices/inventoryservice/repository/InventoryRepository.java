package com.learningmicroservices.inventoryservice.repository;

import com.learningmicroservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
   // Optional<Inventory> findBySkuCode();

//    @Query("SELECT i FROM inventory i WHERE i.sku_code = ?1")
//    List<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
