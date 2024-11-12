package com.msd.customer.service;

import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.exception.ResourceNotFoundException;
import com.msd.customer.model.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer customer) throws ResourceAlreadyExistsException;

	public Customer searchCustomer(String customerId) throws ResourceNotFoundException;

	public Customer updateCustomer(String customerId, Customer customer) throws ResourceNotFoundException;

	public String deleteCustomer(String customerId) throws ResourceNotFoundException;
}
