package com.springboot.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemReq {
    private String eventCode;
    private Integer price;
    private Integer quantity;
}
