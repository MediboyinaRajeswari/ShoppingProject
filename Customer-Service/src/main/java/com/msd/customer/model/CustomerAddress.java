package com.msd.customer.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address")
public class CustomerAddress {
	@Id
	private String addressId;
	private String doorNo;
	private String streetName;
	private String layout;
	private String city;
	private String pincode;

}
