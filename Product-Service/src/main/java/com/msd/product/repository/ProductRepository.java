package com.msd.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository <Product,Long>{

	
	public List<Product> findByProductPriceBetween(double startPrice, double endPrice);
	
	public Product findByProductName(String productName);
	
	
	
	
	
	
}
