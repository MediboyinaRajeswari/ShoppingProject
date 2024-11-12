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
import com.msd.customer.model.CustomerAddress;
import com.msd.customer.service.CustomerAddressService;
/**
 * Customer Address Controller
 * @author SH20379879
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressService customerAddressService;
	
	/**
	 * Method Name: addCustomerAddress, This method add Address to Customer.
	 * @param address
	 * @return
	 * @throws ResourceAlreadyExistsException
	 */
	
	@PostMapping("/addCustomerAddress")
	public ResponseEntity<CustomerAddress> addCustomerAddress(@RequestBody CustomerAddress address) throws ResourceAlreadyExistsException{
		return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressService.addCustomerAddress(address));
	}
	/**
	 * Method Name: searchCustomerAddress, This method is to Fetch specific Address using Address ID.
	 * @param customerAddressId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/searchCustomerAddress/{customerAddressId}")
	public ResponseEntity<CustomerAddress> searchCustomerAddress(@PathVariable("customerAddressId") String customerAddressId) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerAddressService.searchCustomerAddress(customerAddressId));
	}
	/**
	 * Method Name: updateCustomer, This method updates existing Customer Address.
	 * @param customerAddressId
	 * @param customerAddress
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@PutMapping("/updateCustomerAddress/{customerAddressId}")
	public ResponseEntity<CustomerAddress> updateCustomerAddress(@PathVariable("customerAddressId") String customerAddressId, @RequestBody CustomerAddress customerAddress) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerAddressService.updateCustomerAddress(customerAddressId, customerAddress));
	}
	/**
	 * Method Name: deleteCustomerAddress ,This method deletes specific Address.
	 * @param customerAddressId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	
	@DeleteMapping("/deleteCustomerAddress/{customerAddressId}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable("customerAddressId") String customerAddressId) throws  ResourceNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(customerAddressService.deleteCustomerAddress(customerAddressId));
	}
}
