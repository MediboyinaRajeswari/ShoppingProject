package com.msd.shopping.model;

import lombok.Data;


@Data
public class Customer {

	private String customerId;
	private String customerName;
	private String customerEmail;

	private CustomerAddress customerBillingAddress;

	private CustomerAddress customerShippingAddress;
	
}
