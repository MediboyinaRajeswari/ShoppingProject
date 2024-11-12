package com.msd.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.msd.cart.model.LineItem;

public interface LineItemRepository extends MongoRepository<LineItem, String> {

}
