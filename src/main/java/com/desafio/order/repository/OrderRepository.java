package com.desafio.order.repository;

import com.desafio.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    boolean existsByOrderId(String orderId);
    Optional<Order> findByOrderId(String orderId);
}
