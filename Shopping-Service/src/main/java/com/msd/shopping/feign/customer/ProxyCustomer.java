package com.msd.shopping.feign.customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msd.shopping.model.Customer;

@FeignClient(name="Customer-Service")
public interface ProxyCustomer {

	@PostMapping("/api/customer/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer);

	@GetMapping("/api/customer/searchCustomer/{customerId}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable("customerId") String customerId);
	
	@PutMapping("/api/customer/updateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody Customer customer);
	
	@DeleteMapping("/api/customer/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") String customerId);
	
}
