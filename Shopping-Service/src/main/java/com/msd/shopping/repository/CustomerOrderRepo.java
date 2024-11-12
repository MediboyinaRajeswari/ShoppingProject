package com.msd.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.shopping.model.CustomerOrder;

@Repository
public interface CustomerOrderRepo extends MongoRepository<CustomerOrder, String> {

	public CustomerOrder findByCustomerId(String customerId);
}
