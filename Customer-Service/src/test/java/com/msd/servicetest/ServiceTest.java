package com.msd.servicetest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.msd.customer.exception.ResourceAlreadyExistsException;
import com.msd.customer.model.Customer;
import com.msd.customer.model.CustomerAddress;
import com.msd.customer.repository.CustomerAddressRepository;
import com.msd.customer.repository.CustomerRepository;
import com.msd.customer.service.CustomerServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CustomerServiceImpl.class)
public class ServiceTest {
	@Mock
	private CustomerRepository customerrepo;
	@Mock
	private CustomerAddressRepository customeraddressrepo;
	
	@MockBean
	private CustomerServiceImpl customerimpl;
	private Customer customer =new Customer();
	@BeforeEach
	public void Initialize() {
		customer.setCustomerName("Anil Reddy");
		customer.setCustomerEmail("aniul@gmail.com");
		customer.setCustomerId("23");		
		customer.setCustomerBillingAddress(new CustomerAddress("2","121","hyderabd","gandhinagar","hyderabad","90879"));
		customer.setCustomerShippingAddress(new CustomerAddress("22","121","hyder","gandhin","hydera","908"));
		
		
	}
	
	@Test
	public void addCustomerTest() throws ResourceAlreadyExistsException {
		Mockito.when(customeraddressrepo.save(customer.getCustomerBillingAddress())).thenReturn(customer.getCustomerBillingAddress());
		Mockito.when(customeraddressrepo.save(customer.getCustomerShippingAddress())).thenReturn(customer.getCustomerShippingAddress());
		Mockito.when(customerrepo.save(customer)).thenReturn(customer);
		assertEquals(customer, customerimpl.addCustomer(customer));
	}
//	@Test
//	public void SearchCustomer() throws  ResourceNotFoundException {
//		
//		Mockito.when(customerimpl.searchCustomer(customer.getCustomerId())).thenReturn(customer);
//		assertEquals(customer, customerimpl.searchCustomer(customer.getCustomerId()));
//		
//	}
//	@Test
//	public void updateCustomerAddress() throws ResourceNotFoundException{
//	
//	Mockito.when(customerimpl.updateCustomer(customer.getCustomerId(), customer)).thenReturn(customer);
//	assertEquals(customer, customerimpl.updateCustomer(customer.getCustomerId(), customer));
//	
//	
	}
//}
