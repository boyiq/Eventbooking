package com.springboot.inventoryservice.repository;

import com.springboot.inventoryservice.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    Inventory findByEventCode(String eventCode);
}
