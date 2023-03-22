package com.springboot.orderservice.service;

import com.springboot.orderservice.model.OrderItem;
import com.springboot.orderservice.repository.OrderRepository;
import com.springboot.orderservice.repository.OrderItemRepository;
import com.springboot.orderservice.model.Order;
import com.springboot.orderservice.model.OrderItemReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public void createOrder(List<OrderItemReq> newItemsReqList) {
        Order order = new Order();
        String newOrderNumber = UUID.randomUUID().toString();
        System.out.println(newOrderNumber);

        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemReq eachOrderItemReq : newItemsReqList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setEvent_code(eachOrderItemReq.getEventCode());
            orderItem.setPrice(eachOrderItemReq.getPrice());
            orderItem.setQuantity(eachOrderItemReq.getQuantity());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }

        order.setOrderItemList(orderItemList);
        order.setOrder_number(newOrderNumber);
        order = orderRepository.save(order);
        log.info("new order {} is saved", order.getOrder_number());
    }
}
