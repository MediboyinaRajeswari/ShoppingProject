package com.msd.shopping.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart {
	
	private String cartId;

	private List<LineItem> lineItems = new ArrayList<>();

}
