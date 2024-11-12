package com.msd.cart.testRepo;

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
import org.springframework.test.context.ActiveProfiles;

import com.msd.cart.model.Cart;
import com.msd.cart.repository.CartRepository;


@SpringBootTest(classes = com.msd.cart.CartServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("CartRepositoryTest")
@ActiveProfiles("test")
 class TestCartRepository {
	
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
		Cart result = cartRepository.findById(cart.getCartId()).get();
		assertEquals(cart.getCartId(), result.getCartId());
	}
	
	@Test
	@Order(2)
	@DisplayName("Test-> Find Cart By Id")
	void testFindById() {
		Cart result = cartRepository.findById(cart.getCartId()).get();
		assertEquals(cart.getCartId(), result.getCartId());
	}
	
	
	@Test
	@Order(3)
	@DisplayName("Test-> Get All Carts")
	void testFindAll() {
		List<Cart> result = new ArrayList<Cart>();
		cartRepository.findAll().forEach(e -> result.add(e));
		assertEquals(1, result.size());
	}
	
	
	@Test
	@Order(4)
	@DisplayName("Test-> Delete Cart By Id")
	void testDeleteById() {
		Cart result = cartRepository.findById(cart.getCartId()).get();
		cartRepository.deleteById(result.getCartId());
		List<Cart> result1 = new ArrayList<>();
		cartRepository.findAll().forEach(e -> result1.add(e));
		assertEquals(0, result1.size());
	}
	
	
	
	private Cart getCart() {
		Cart cart=new Cart();
		return cart;
		
	}

}
