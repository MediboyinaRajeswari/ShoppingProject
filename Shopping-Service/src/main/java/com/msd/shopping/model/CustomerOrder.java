package com.msd.shopping.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Customer-Order")
public class CustomerOrder {
	
	private String customerId;
	
	private String orderId;
}
