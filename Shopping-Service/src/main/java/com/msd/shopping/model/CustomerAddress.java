package com.msd.shopping.model;

import lombok.Data;

@Data
public class CustomerAddress {
	
	private String addressId;
	private String doorNo;
	private String streetName;
	private String layout;
	private String city;
	private String pincode;

}
