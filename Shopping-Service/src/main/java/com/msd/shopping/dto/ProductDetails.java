package com.msd.shopping.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "productDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
	
	private long id;
	private String productName;
	private String productDescription;
	private double productPrice;
    private  int quantity;

}
