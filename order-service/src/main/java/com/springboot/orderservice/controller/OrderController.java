package com.springboot.orderservice.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.orderservice.service.OrderService;
import com.springboot.orderservice.model.OrderItemReq;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @GetMapping
    private ResponseEntity<String> getTest() {
        try {
            return new ResponseEntity<>("Get works", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Doens't work", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    private ResponseEntity<String> createOrder(@RequestBody List<OrderItemReq> newItemsList) {
        try {
            orderService.createOrder(newItemsList);
            return new ResponseEntity<>("New order placed", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to place order" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
