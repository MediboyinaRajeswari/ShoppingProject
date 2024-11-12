package com.msd.cart.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msd.cart.exception.ResourceNotFoundException;
import com.msd.cart.model.Cart;
import com.msd.cart.model.LineItem;
import com.msd.cart.repository.CartRepository;

/**
 * 
 * @author MO20364776
 *
 */

@Service
public class CartService {

	@Autowired
	CartRepository cartRepo;

	@Autowired
	LineItemService lineItemService;

	private static Logger log = LoggerFactory.getLogger(CartService.class);

	/**
	 * Method Name: addCart,This method is used for adding new cart
	 * 
	 * @param cartData
	 * @return cartId and 201 HTTP code
	 */
	
	public ResponseEntity<Cart> addCart(Cart cartData) {
		Cart cart = new Cart();
		cartData.getLineItems().stream().forEach(lineItem -> lineItemService.addLineItem(lineItem));
		cart.setLineItems(cartData.getLineItems());
		Cart savedCart = cartRepo.save(cart);
		log.info("Cart Created");
		return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
	}
	
	/**
	 * Method Name: searchCart,This method is used to find Cart by cartId
	 * @param id
	 * @return cart and 200 HTTP code
	 */

	public ResponseEntity<Cart> searchCart(String id) {
		Cart cart = cartRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cart Not Found for Id: " + id));
		log.info("Cart By Id");
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	/**
	 * Method Name: emptyCart,This method is used to Delete Cart by cartId
	 * @param id
	 * @return message and 200 HTTP code
	 */
	public ResponseEntity<String> emptyCart(String id) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));
		
		cart.getLineItems().stream().map(LineItem::getItemId).forEach(itemId -> lineItemService.deleteLineItem(itemId));
		cartRepo.deleteById(id);
		log.info("Cart is deleted");
		return new ResponseEntity<>("Cart with id= " + id + " is Deleted", HttpStatus.OK);
	}

	/**
	 * Method Name: updateCart,This method is used to Update Cart by cartId
	 * @param id
	 * @param cartData
	 * @return message and 200 HTTP code
	 */
	public ResponseEntity<Cart> updateCart(String id, Cart cartData) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

		cart.getLineItems().stream().map(LineItem::getItemId).forEach(itemId -> lineItemService.deleteLineItem(itemId));
		List<LineItem> lineItems = new ArrayList<>();

		cartData.getLineItems().stream().forEach(lineItem -> lineItemService.addLineItem(lineItem));
		cartData.getLineItems().stream().forEach(lineItems::add);
		cart.setLineItems(lineItems);
		Cart updatedCart = cartRepo.save(cart);
		log.info("Cart Updated");
		return new ResponseEntity<>(updatedCart, HttpStatus.OK);
	}

	/**
	 * Method Name: listCart,This method is used to get list of carts
	 * @return list of cart and 200 HTTP code
	 */
	public ResponseEntity<List<Cart>> listCart() {
		List<Cart> list = cartRepo.findAll();
		if (list.isEmpty())
			throw new ResourceNotFoundException("No Cart Found");
		else {
			log.info("List Of Cart");
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

}
