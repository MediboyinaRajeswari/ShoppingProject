package com.msd.shopping.feign.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msd.shopping.model.Order;

@FeignClient(name="Order-Service")
public interface ProxyOrder {
	
	@PostMapping("/api/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order);

	@GetMapping("/api/order/{id}")
	public ResponseEntity<Order> searchOrder(@PathVariable String id);
	
	@PutMapping("/api/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order);
	
	@DeleteMapping("/api/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable String id);
}
	
