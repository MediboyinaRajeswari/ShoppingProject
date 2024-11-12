package com.msd.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.exception.ResourceNotFoundException;
import com.msd.customer.model.Customer;
import com.msd.customer.model.CustomerAddress;
import com.msd.customer.repository.CustomerAddressRepository;
import com.msd.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Override
	public Customer addCustomer(Customer customer) throws ResourceAlreadyExistsException {
		
		
		CustomerAddress customerBillingAddress = customer.getCustomerBillingAddress();
        customerAddressRepository.save(customerBillingAddress);
        
        CustomerAddress customerShippingAddress = customer.getCustomerShippingAddress();
        customerAddressRepository.save(customerShippingAddress);
        
        Customer savedCustomer = new Customer();

        savedCustomer.setCustomerName(customer.getCustomerName());

        savedCustomer.setCustomerEmail(customer.getCustomerEmail());

        savedCustomer.setCustomerBillingAddress(customerBillingAddress);

        savedCustomer.setCustomerShippingAddress(customerShippingAddress);

        return customerRepository.save(savedCustomer);

	}

	@Override
	public Customer searchCustomer(String customerId) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with this ID doesn't exist"));

		return customer;
	}

	@Override
	public Customer updateCustomer(String customerId, Customer customer) throws ResourceNotFoundException {
		Customer savedCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with this ID doesn't exist"));
       
		savedCustomer.setCustomerName(customer.getCustomerName());
		savedCustomer.setCustomerEmail(customer.getCustomerEmail());
		
		CustomerAddress billingAddress = customer.getCustomerBillingAddress();
		billingAddress.setAddressId(savedCustomer.getCustomerBillingAddress().getAddressId());
        savedCustomer.setCustomerBillingAddress(billingAddress);
        
        CustomerAddress shippingAddress = customer.getCustomerShippingAddress();
        shippingAddress.setAddressId(savedCustomer.getCustomerShippingAddress().getAddressId());
        savedCustomer.setCustomerShippingAddress(shippingAddress);
        
		return customerRepository.save(savedCustomer);
	}

	@Override
	public String deleteCustomer(String customerId) throws ResourceNotFoundException {
		customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with this ID doesn't exist"));
		customerRepository.deleteById(customerId);
		return "Customer deleted successfully";
	}

}
