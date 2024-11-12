package com.msd.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.shopping.dto.ProductDetails;
import com.msd.shopping.model.Cart;
import com.msd.shopping.model.Customer;
import com.msd.shopping.model.Order;
import com.msd.shopping.service.ShoppingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/shoppingservice")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Welcome to Shopping Service";
	}

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@PostMapping("/products")
	public ResponseEntity<String> addProductDetails(@RequestBody ProductDetails productDetails) {
		String message = shoppingService.addProductDetails(productDetails);
		return new ResponseEntity<>(message, HttpStatus.CREATED);

	}

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		return shoppingService.addCustomer(customer);
	}

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@PutMapping("/customer/{customerId}/cart")
	public ResponseEntity<Cart> updateCart(@PathVariable String customerId, @RequestBody Cart cart) {
		return shoppingService.updateCart(customerId, cart);
	}

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@PostMapping("/customer/{customerId}/order")
	public ResponseEntity<Order> addOrder(@PathVariable String customerId) {
		return shoppingService.addOrder(customerId);
	}

	@CircuitBreaker(fallbackMethod = "defaultMethodforService", name = "Service")
	@GetMapping("/customer/{customerId}/orders")
	public ResponseEntity<Object> orders(@PathVariable String customerId) {
		return shoppingService.orders(customerId);
	}

	// Circuit Breaker FallBack
	public ResponseEntity<Object> defaultMethodforService(RuntimeException e) {
		return new ResponseEntity<>("Sorry, We are trying to solve.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
