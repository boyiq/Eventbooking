package com.springboot.inventoryservice.controller;

import com.springboot.inventoryservice.model.InventoryRes;
import com.springboot.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    private ResponseEntity<List<InventoryRes>> inStock(List<String> eventCodes) {
        try {
            List<InventoryRes> inStockRes = inventoryService.checkStock(eventCodes);
            return new ResponseEntity<List<InventoryRes>>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
