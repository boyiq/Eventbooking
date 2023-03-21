package com.springboot.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import com.springboot.orderservice.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
