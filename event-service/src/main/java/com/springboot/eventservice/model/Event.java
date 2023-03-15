package com.springboot.eventservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "event")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    private Integer id;
    private String name;
    private String address;
    private String city;
    private int price;
}
