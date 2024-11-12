package com.msd.product.service;

import java.util.List;


import com.msd.product.dto.ProductDto;
import com.msd.product.model.Product;

public interface ProductServiceInterface {

	
	public Product addProduct(ProductDto productDto);
	
	public Product updateProduct(long productId,Product product);
	
	public void deleteProduct(long productId);
	
	public Product getProduct(long productId);
	
	public List<Product> findByProductPriceBetween(double priceMin,double priceMax);
	
	public void deleteAll();
	
	public Product getProductByName(String productName);
	
	public List<Product> getAll();
	
}
