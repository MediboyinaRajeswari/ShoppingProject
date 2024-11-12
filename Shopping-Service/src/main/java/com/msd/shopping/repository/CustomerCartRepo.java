package com.msd.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.shopping.model.CustomerCart;

@Repository
public interface CustomerCartRepo extends MongoRepository<CustomerCart, String> {
	
	public CustomerCart findByCustomerId(String customerId);
	
	public CustomerCart findByCartId(String cartId);

}
