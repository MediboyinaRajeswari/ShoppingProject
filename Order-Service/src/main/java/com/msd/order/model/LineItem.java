package com.msd.order.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class LineItem {

	@Id
	private String itemId;

	private long productId;

	private String productName;

	private long quantity;

	private double price;
}
