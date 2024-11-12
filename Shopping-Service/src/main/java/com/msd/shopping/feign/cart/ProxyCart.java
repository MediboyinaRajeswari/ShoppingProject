package com.msd.shopping.feign.cart;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msd.shopping.model.Cart;

@FeignClient(name="Cart-Service")
public interface ProxyCart {
	
	@PostMapping("/api/cart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart);

	@GetMapping("/api/cart/{id}")
	public ResponseEntity<Cart> searchCart(@PathVariable String id);
	
	@PutMapping("/api/cart/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable String id, @RequestBody Cart cart);
	
	@GetMapping("/api/cart/list")
	public ResponseEntity<List<Cart>> listCart();
	
	@DeleteMapping("/api/cart/{id}")
	public ResponseEntity<String> emptyCart(@PathVariable String id);
	
}
