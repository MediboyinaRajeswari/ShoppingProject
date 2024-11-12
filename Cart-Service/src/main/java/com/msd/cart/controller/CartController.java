package com.msd.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.cart.dto.CartDTO;
import com.msd.cart.model.Cart;
import com.msd.cart.service.CartService;

/**
 * Cart Controller
 * 
 * @author MO20364776
 *
 */

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartService service;

	/**
	 * Method Name:addCart, This method is used for add new Cart
	 * 
	 * @param cartDTO, This is parameter to this method
	 * @return cart and 201 HTTP code
	 * @see CartController
	 */
	@PostMapping("/cart")
	public ResponseEntity<Cart> addCart(@RequestBody CartDTO cartDTO) {
		return service.addCart(cartDTO.dtoToCart());
	}

	/**
	 * Method searchCart, This method is used to search cart by cartId
	 * 
	 * @param id, This is parameter to this method
	 * @return cart and 200 HTTP code
	 * @see CartController
	 */
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> searchCart(@PathVariable String id) {
		return service.searchCart(id);
	}

	/**
	 * Method updateCart, This method is used to update the cart
	 * 
	 * @param id, This is parameter1 to this method
	 * @param cartDTO, This is parameter2 to this method
	 * @return cart and 200 HTTP code
	 * @see CartController
	 */
	@PutMapping("/cart/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable String id, @RequestBody CartDTO cartDTO) {
		return service.updateCart(id, cartDTO.dtoToCart());
	}

	/**
	 * Method deleteCart, This method is used to delete the cart
	 * 
	 * @param id, This is parameter to this method
	 * @return 200 HTTP code
	 * @see CartController
	 */
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> emptyCart(@PathVariable String id) {
		return service.emptyCart(id);
	}

	/**
	 * Method listCart, This method is used to get List of Carts
	 * 
	 * @return List and 200 HTTP code
	 * @see CartController
	 */
	@GetMapping("/cart/list")
	public ResponseEntity<List<Cart>> listCart() {
		return service.listCart();
	}
}
