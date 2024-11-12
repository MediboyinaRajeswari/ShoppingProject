package com.msd.cart.testService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.msd.cart.model.Cart;
import com.msd.cart.model.LineItem;
import com.msd.cart.repository.CartRepository;
import com.msd.cart.service.CartService;

/**
 * 
 * @author MO20364776
 *
 */

@SpringBootTest(classes = com.msd.cart.CartServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("CartServiceTest")
@ActiveProfiles("test")
 class TestCartService {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartRepository cartRepository;
	
	Cart cart=getCart();
	
	@BeforeEach
	public void startCart() {
		cart = cartRepository.save(cart);
	}
	@AfterEach
	public void stopCart() {
		cartRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	@DisplayName("Test-> Save Cart")
	void testSave() {
		ResponseEntity<Cart> result = cartService.addCart(cart);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	@Order(2)
	@DisplayName("Test-> Find Cart By Id")
	void testFindById() {
		Cart result = cartService.searchCart(cart.getCartId()).getBody();
		assertEquals(cart.getCartId(), result.getCartId());
	}
	
	@Test
	@Order(3)
	@DisplayName("Test-> Update Cart By Id")
	void testUpdateById() {
		cart.setLineItems(getLineItems());
		ResponseEntity<Cart> result = cartService.updateCart(cart.getCartId(), cart);
		assertEquals(cart, cartRepository.findById(cart.getCartId()).get());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	
	@Test
	@Order(4)
	@DisplayName("Test-> Get All Carts")
	void testFindAll() {
		List<Cart> list = cartService.listCart().getBody();
		assertEquals(1, list.size());
	}
	
	
	@Test
	@Order(5)
	@DisplayName("Test-> Delete Cart By Id")
	void testDeleteById() {
		Cart result = cartRepository.findById(cart.getCartId()).get();
		cartService.emptyCart(result.getCartId());
		List<Cart> result1 = new ArrayList<>();
		cartRepository.findAll().forEach(e -> result1.add(e));
		assertEquals(0, result1.size());
	}
	
	
	
	private Cart getCart() {
		Cart cart=new Cart();
		return cart;
		
	}
	
	private List<LineItem> getLineItems() {
		List<LineItem> list = new ArrayList<>();
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1001L);
		lineItem.setProductName("Samsung Galaxy");
		lineItem.setQuantity(2L);
		lineItem.setPrice(100);
		list.add(lineItem);
		return list;
		
	}

}
