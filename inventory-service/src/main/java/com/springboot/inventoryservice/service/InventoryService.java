package com.springboot.inventoryservice.service;

import com.springboot.inventoryservice.model.Inventory;
import com.springboot.inventoryservice.model.InventoryRes;
import com.springboot.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryRes> checkStock (List<String> eventCodes) {
        List<InventoryRes> resultList = new ArrayList<>();
        for (String eachEventCode:eventCodes) {
            Inventory inventory = inventoryRepository.findByEventCode(eachEventCode);
            boolean isInStock;
            if (inventory != null) {
                isInStock = inventory.getStock_quantity() > 0;
            } else {
                isInStock = false;
            }

            InventoryRes result = InventoryRes.builder()
                    .event_code(inventory.getEventCode())
                    .inStock(isInStock)
                    .build();
            resultList.add(result);
        }
        return resultList;
    }

}
