package com.msd.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.customer.model.CustomerAddress;
@Repository
public interface CustomerAddressRepository extends MongoRepository<CustomerAddress, String>{

}
