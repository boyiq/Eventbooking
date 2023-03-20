package com.springboot.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String eventCode;

    private Integer price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "order_id")
    private Order order;
}
