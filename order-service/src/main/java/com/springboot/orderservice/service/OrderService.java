package com.springboot.orderservice.service;

import com.springboot.orderservice.model.OrderItem;
import com.springboot.orderservice.repository.OrderRepository;
import com.springboot.orderservice.model.Order;
import com.springboot.orderservice.model.OrderItemReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(List<OrderItemReq> newItemsList) {
        String newOrderNumber = UUID.randomUUID().toString();

        newItemsList.stream().map(orderItemReq -> mapTo(orderItemReq));
        Order newOrder = Order.builder()
                        .orderItemList(newItemsList)
                        .orderNumber(newOrderNumber)
                        .build();

        orderRepository.save(newOrder);
        log.info("new order {} is saved", newOrder.getOrderNumber());
    }

    private OrderItem mapTo()

    public void saveOrderItems(List<OrderItem> newItemsList) {

    }

}
