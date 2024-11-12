package com.msd.shopping.model;

import lombok.Data;

@Data
public class LineItem {
	
	private String itemId;

	private long productId;

	private String productName;

	private long quantity;

	private double price;

}
