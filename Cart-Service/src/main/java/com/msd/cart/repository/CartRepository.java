package com.msd.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.cart.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

}
