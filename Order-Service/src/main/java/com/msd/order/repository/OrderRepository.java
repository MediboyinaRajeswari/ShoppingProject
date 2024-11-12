package com.msd.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.order.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
