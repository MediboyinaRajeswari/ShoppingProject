package com.msd.shopping.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Customer-Cart")
public class CustomerCart {
	
	private String customerId;
	private String cartId;
}
