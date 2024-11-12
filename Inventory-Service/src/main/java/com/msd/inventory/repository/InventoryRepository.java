package com.msd.inventory.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.inventory.model.Inventory;


@Repository
public interface InventoryRepository extends MongoRepository<Inventory,String>{

	Inventory findByProductId(Long productId);


	
}
