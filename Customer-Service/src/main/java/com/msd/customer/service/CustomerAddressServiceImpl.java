package com.msd.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.exception.ResourceNotFoundException;
import com.msd.customer.model.CustomerAddress;
import com.msd.customer.repository.CustomerAddressRepository;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService{
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	@Override
	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) throws ResourceAlreadyExistsException {
		if (customerAddressRepository.findById(customerAddress.getAddressId()).isPresent()) {
			throw new ResourceAlreadyExistsException("Customer Address with this ID already exists");
		}
		
	

		return customerAddressRepository.save(customerAddress);
	}

	@Override
	public CustomerAddress searchCustomerAddress(String customerAddressId) throws ResourceNotFoundException {
		CustomerAddress customerAddress = customerAddressRepository.findById(customerAddressId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Address with this ID doesn't exist"));

		return customerAddress;
	}

	@Override
	public CustomerAddress updateCustomerAddress(String customerAddressId, CustomerAddress customerAddress)
			throws ResourceNotFoundException {
		CustomerAddress savedCustomerAddress = customerAddressRepository.findById(customerAddressId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Address with this ID doesn't exist"));

		customerAddress.setAddressId(savedCustomerAddress.getAddressId());
		return customerAddressRepository.save(customerAddress);
	}

	@Override
	public String deleteCustomerAddress(String customerAddressId) throws ResourceNotFoundException {
		customerAddressRepository.findById(customerAddressId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Address with this ID doesn't exist"));
		customerAddressRepository.deleteById(customerAddressId);
		return "Customer Address deleted successfully";
	}
}
