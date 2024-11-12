package com.msd.shopping.feign.product;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.msd.shopping.dto.ProductDto;
import com.msd.shopping.model.Product;


@FeignClient(value="Product-Service")
public interface ProxyProduct {

	@PostMapping("/api/products/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto);
	
	
	@GetMapping("/api/products/get/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable long productId);
	
	@GetMapping("/api/products/getProduct/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) ;
	
	@GetMapping("/api/products/get/{productMin}/To/{productMax}")
	public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable double productMin,@PathVariable double productMax);
	
	@PutMapping("/api/products/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable long productId,@RequestBody ProductDto productDto);
	
	@DeleteMapping("/api/products/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable long productId);
	
	@DeleteMapping("/api/products/deleteAll")
	public ResponseEntity<String> deleteAll();
	
	@GetMapping("/api/products/getAll")
	public ResponseEntity<List<Product>> getAll();
}
