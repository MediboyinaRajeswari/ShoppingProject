package com.msd.customer.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customerdetails")
public class Customer {
	@Id
	private String customerId;
	private String customerName;
	private String customerEmail;
	@DocumentReference
	private CustomerAddress customerBillingAddress;
	@DocumentReference
	private CustomerAddress customerShippingAddress;
}
