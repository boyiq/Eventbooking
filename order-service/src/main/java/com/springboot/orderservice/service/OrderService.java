package com.springboot.orderservice.service;

import com.springboot.orderservice.event.OrderCompleteEvent;
import com.springboot.orderservice.model.OrderItem;
import com.springboot.orderservice.repository.OrderRepository;
import com.springboot.orderservice.repository.OrderItemRepository;
import com.springboot.orderservice.model.Order;
import com.springboot.orderservice.model.OrderItemReq;
import com.springboot.orderservice.model.InventoryRes;
import com.springboot.orderservice.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final KafkaTemplate<String, OrderCompleteEvent> kafkaTemplate;

    @Autowired
    private final WebClient.Builder webClientBuilder;

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

        List<String> eventCodes = order.getOrderItemList().stream().map(OrderItem::getEvent_code).toList();

        InventoryRes[] inventoryResList =  webClientBuilder.build().get()
                .uri("http://inventory-services/inventory", uriBuilder -> uriBuilder.queryParam("eventCodes", eventCodes).build())
                .retrieve()
                .bodyToMono(InventoryRes[].class)
                .block();
        assert inventoryResList != null;
        boolean allInStock = Arrays.stream(inventoryResList).allMatch(InventoryRes::isInStock);

        if (allInStock) {
            order = orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderCompleteEvent(order.getOrder_number()));
            log.info("new order {} is placed", order.getOrder_number());
        } else {
            throw new IllegalArgumentException("Certain item in order not in stock");
        }


    }
}
