package com.springboot.inventoryservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_code")
    private String eventCode;

    private Integer stock_quantity;
}
