package com.msd.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
@Document("carts")
public class Cart {

	@Id
	private String cartId;

	@DocumentReference
	private List<LineItem> lineItems = new ArrayList<>();

}
