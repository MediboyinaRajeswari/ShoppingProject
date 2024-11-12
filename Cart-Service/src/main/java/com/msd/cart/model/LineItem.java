package com.msd.cart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("LineItems")
public class LineItem {

	@Id
	private String itemId;

	private long productId;

	private String productName;

	private long quantity;

	private double price;

}
