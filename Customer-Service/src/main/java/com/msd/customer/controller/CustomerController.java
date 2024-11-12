package com.msd.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.exception.ResourceNotFoundException;
import com.msd.customer.model.Customer;
import com.msd.customer.service.CustomerService;
/**
 * Cart Controller
 * 
 * @author SH20379879
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	/**
	 * Method Name:addCustomer, This method is used for add new Customer
	 * 
	 *
	 * @return customer and 201 HTTP code
	 * @see CustomerController
	 */
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws ResourceAlreadyExistsException{
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
	}
	/**
	 * Method Name: searchCustomer, This method is to fetch specific customer using customerID
	 * @param customerId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/searchCustomer/{customerId}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable("customerId") String customerId) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.searchCustomer(customerId));
	}
	/**
	 * Method Name: updateCustomer, This method updates specific customer details
	 * @param customerId
	 * @param customer
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody Customer customer) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerId, customer));
	}
	/**
	 * Method Name: deleteCustomer, This method is to delete customer with specific ID.
	 * @param customerId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") String customerId) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(customerId));
	}
}
