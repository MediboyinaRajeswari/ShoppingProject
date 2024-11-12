package com.msd.cart.dto;

import java.util.ArrayList;
import java.util.List;

import com.msd.cart.model.Cart;
import com.msd.cart.model.LineItem;

import lombok.Data;

@Data
public class CartDTO {

	private String cartId;

	private List<LineItem> lineItems = new ArrayList<>();
	
	public Cart dtoToCart() {
		Cart cart = new Cart();
		cart.setCartId(this.cartId);
		cart.setLineItems(this.lineItems);
		return cart;
	}

}
