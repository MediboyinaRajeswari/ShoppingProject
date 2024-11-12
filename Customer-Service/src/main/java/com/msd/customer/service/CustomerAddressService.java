package com.msd.customer.service;

import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.exception.ResourceNotFoundException;
import com.msd.customer.model.CustomerAddress;

public interface CustomerAddressService {

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress)throws ResourceAlreadyExistsException;

	public CustomerAddress searchCustomerAddress(String customerAddressId)throws ResourceNotFoundException;

	public CustomerAddress updateCustomerAddress(String customerAddressId, CustomerAddress customerAddress)throws ResourceNotFoundException;

	public String deleteCustomerAddress(String customerAddressId)throws ResourceNotFoundException;

}
